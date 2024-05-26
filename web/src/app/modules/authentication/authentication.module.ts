import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthenticationRoutingModule } from './authentication-routing.module';
import { PageRegisterComponent } from './pages/page-register/page-register.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormsModule } from '@angular/forms';
import { PageLoginComponent } from './pages/page-login/page-login.component';



@NgModule({
  declarations: [
    PageRegisterComponent,
    PageLoginComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    AuthenticationRoutingModule
  ]
})
export class AuthenticationModule { }
