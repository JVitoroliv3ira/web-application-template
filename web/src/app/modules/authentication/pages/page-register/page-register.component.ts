import { Component } from '@angular/core';

import { RegisterRequestDTO } from 'src/app/core/dtos/requests/register-request.dto';

@Component({
  selector: 'app-page-register',
  templateUrl: './page-register.component.html'
})
export class PageRegisterComponent {
  public request: RegisterRequestDTO = {} as RegisterRequestDTO;
  public errors: string[] = []


  public handleRegisterClick(): void {
    this.errors = [];
    this.validateRequest();
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

  public get formIsDisabled(): boolean {
    const { name, email, password } = this.request;

    return name === null || name === undefined || email === null || email === undefined || password === null || password === undefined;
  }
}

