import { Component, ElementRef, Input, ViewChild } from '@angular/core';

@Component({
  selector: 'app-error-badge',
  templateUrl: './error-badge.component.html'
})
export class ErrorBadgeComponent {
  @Input() errors: string[] = [];
}
