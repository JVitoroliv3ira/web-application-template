import { Component } from '@angular/core';
import { take } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

import { RegisterRequestDTO } from 'src/app/core/dtos/requests/register-request.dto';
import { ApiResponseDTO } from 'src/app/core/dtos/responses/api-response.dto';
import { AuthenticationService } from 'src/app/core/services/authentication/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-page-register',
  templateUrl: './page-register.component.html'
})
export class PageRegisterComponent {
  public request: RegisterRequestDTO = {} as RegisterRequestDTO;
  public errors: string[] = []

  constructor(
    private authService: AuthenticationService,
    private router: Router
  ) {}

  public handleRegisterClick(): void {
    this.errors = [];
    this.validateRequest();
    this.register();
  }

  private validateRequest(): void {
    const { name, email, password } = this.request;

    if (name === null || name === undefined) {
      this.errors.push('Informe o seu nome.');
    } else if (name.trim().length < 5 || name.trim().length > 80) {
      this.errors.push('O nome deve conter entre 5 e 80 caracteres.');
    }

    if (email === null || email === undefined) {
      this.errors.push('Informe o seu e-mail.');
    } else if (email.trim().length < 9 || email.trim().length > 90) {
      this.errors.push('O e-mail deve conter entre 9 e 90 caracteres.');
    }

    if (password === null || password === undefined) {
      this.errors.push('Informe a sua senha.');
    } else if (password.trim().length < 6) {
      this.errors.push('A senha deve conter pelo menos 6 caracteres.');
    }
  }

  public register(): void {
    this.authService
      .register(this.request)
      .pipe(take(1))
      .subscribe({
        next: this.handleRegisterSuccess.bind(this),
        error: this.handleRegisterError.bind(this)
      })
  }

  public handleRegisterSuccess(res: ApiResponseDTO<any>): void {
    this.router.navigateByUrl('/authentication/login');
  }

  public handleRegisterError(err: HttpErrorResponse): void {
    this.errors = err.error.errors ?? ['Ocorreu um erro inesperado durante o cadastro.'];
  }

  public get formIsDisabled(): boolean {
    const { name, email, password } = this.request;

    return name === null || name === undefined || email === null || email === undefined || password === null || password === undefined;
  }
}

