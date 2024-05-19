import { Component, OnInit } from '@angular/core';
import { take } from 'rxjs';
import { VersionService } from 'src/app/core/services/version/version.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html'
})
export class FooterComponent implements OnInit {
  constructor(private versionService: VersionService) {}

  ngOnInit(): void {
      this.setAplicationVersion();
  }

  private setAplicationVersion(): void {
    this.versionService
      .getVersion()
      .pipe(take(1))
      .subscribe({

      });
  }
}
