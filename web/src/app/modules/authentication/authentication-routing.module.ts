import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageRegisterComponent } from './pages/page-register/page-register.component';

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
        component: PageRegisterComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthenticationRoutingModule { }