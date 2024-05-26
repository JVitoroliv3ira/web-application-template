import { Component, OnInit } from '@angular/core';
import { VersionService } from 'src/app/core/services/version/version.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html'
})
export class FooterComponent implements OnInit {

  public version: string | null = null;

  constructor(
    private versionService: VersionService,
  ) {}

  ngOnInit(): void {
    this.versionService.getApplicationVersionObservable()
      .subscribe((v: string | null) => {
        this.version = v;
      })
  }
}
