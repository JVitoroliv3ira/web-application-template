import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorBadgeComponent } from './components/error-badge/error-badge.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { LayoutUnauthenticatedUserComponent } from './layouts/layout-unauthenticated-user/layout-unauthenticated-user.component';



@NgModule({
  declarations: [
    ErrorBadgeComponent,
    FooterComponent,
    LayoutUnauthenticatedUserComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    ErrorBadgeComponent,
    LayoutUnauthenticatedUserComponent
  ]
})
export class SharedModule { }
