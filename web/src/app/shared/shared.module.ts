import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorBadgeComponent } from './components/error-badge/error-badge.component';



@NgModule({
  declarations: [
    ErrorBadgeComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    ErrorBadgeComponent
  ]
})
export class SharedModule { }
