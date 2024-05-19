import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ApiResponseDTO } from 'src/app/core/dtos/responses/api-response.dto';
import { VersionResponseDTO } from 'src/app/core/dtos/responses/version-response.dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class VersionService {
  constructor(private http: HttpClient) {}

  public getVersion(): Observable<ApiResponseDTO<VersionResponseDTO>> {
    return this.http.get<ApiResponseDTO<VersionResponseDTO>>(
      'http://localhost:8080/api/v1/version'
    );
  }
}

