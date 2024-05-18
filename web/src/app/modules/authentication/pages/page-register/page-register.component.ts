import { Component } from '@angular/core';

import { RegisterRequestDTO } from 'src/app/core/dtos/requests/register-request.dto';

@Component({
  selector: 'app-page-register',
  templateUrl: './page-register.component.html'
})
export class PageRegisterComponent {
  public request: RegisterRequestDTO = {} as RegisterRequestDTO;

  public handleRegisterClick(): void {
    console.log(this.request);
  }
}

