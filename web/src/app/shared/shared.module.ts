import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorBadgeComponent } from './components/error-badge/error-badge.component';
import { FooterComponent } from './layouts/footer/footer.component';



@NgModule({
  declarations: [
    ErrorBadgeComponent,
    FooterComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    ErrorBadgeComponent,
    FooterComponent
  ]
})
export class SharedModule { }
