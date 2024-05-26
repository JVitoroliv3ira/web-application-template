import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageRegisterComponent } from './pages/page-register/page-register.component';
import { PageLoginComponent } from './pages/page-login/page-login.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'register',
    pathMatch: 'full'
  },
  {
    path: '',
    children: [
      {
        path: 'register',
        title: 'Register - App',
        component: PageRegisterComponent
      },
      {
        path: 'login',
        title: 'Login - App',
        component: PageLoginComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthenticationRoutingModule { }
