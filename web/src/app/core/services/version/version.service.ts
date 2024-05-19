import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ApiResponseDTO } from 'src/app/core/dtos/responses/api-response.dto';
import { VersionResponseDTO } from 'src/app/core/dtos/responses/version-response.dto';
import { get, set } from 'src/app/core/utils/localstorage-util';

import { environment } from 'src/environments/environment';

const APPLICATION_VERSION_KEY = 'APPLICATION_VERSION';

@Injectable({
  providedIn: 'root',
})
export class VersionService {
  private apiBaseUrl: string = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getVersion(): Observable<ApiResponseDTO<VersionResponseDTO>> {
    return this.http.get<ApiResponseDTO<VersionResponseDTO>>(
      `${this.apiBaseUrl}/version`
    );
  }

  public setApplicationVersion(version: string): void {
    set<string>(APPLICATION_VERSION_KEY, version);
  }

  public getApplicationVersion(): string | null {
    return get<string>(APPLICATION_VERSION_KEY);
  }
}

