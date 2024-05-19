import { Component, OnInit } from '@angular/core';
import { take } from 'rxjs';
import { ApiResponseDTO } from 'src/app/core/dtos/responses/api-response.dto';
import { VersionResponseDTO } from 'src/app/core/dtos/responses/version-response.dto';
import { VersionService } from 'src/app/core/services/version/version.service';

@Component({
  selector: 'app-layout-unauthenticated-user',
  templateUrl: './layout-unauthenticated-user.component.html'
})
export class LayoutUnauthenticatedUserComponent implements OnInit {
  constructor(
    private versionService: VersionService
  ) {}

  ngOnInit(): void {
    this.getApplicationVersion();
  }

  public getApplicationVersion(): void {
    this.versionService
      .getVersion()
      .pipe(take(1))
      .subscribe({
        next: (res: ApiResponseDTO<VersionResponseDTO>) => this.handleApplicationVersionSuccess(res),
        error: () => this.handleApplicationVersionError()
      });
  }

  public handleApplicationVersionSuccess(res: ApiResponseDTO<VersionResponseDTO>): void {
    this.versionService.setApplicationVersion(res.content.version);
  }

  public handleApplicationVersionError(): void {

  }
}
