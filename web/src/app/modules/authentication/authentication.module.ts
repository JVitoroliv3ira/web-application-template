import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthenticationRoutingModule } from './authentication-routing.module';
import { PageRegisterComponent } from './pages/page-register/page-register.component';
import { SharedModule } from 'src/app/shared/shared.module';



@NgModule({
  declarations: [
    PageRegisterComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    AuthenticationRoutingModule
  ]
})
export class AuthenticationModule { }
