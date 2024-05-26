import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { RegisterRequestDTO } from "../../dtos/requests/register-request.dto";
import { Observable } from "rxjs";
import { ApiResponseDTO } from "../../dtos/responses/api-response.dto";

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private apiBaseUrl: string = environment.apiBaseUrl;
  constructor(protected http: HttpClient) {}

  public register(request: RegisterRequestDTO): Observable<ApiResponseDTO<any>> {
    return this.http
      .post<ApiResponseDTO<any>>(
        `${this.apiBaseUrl}/auth/register`,
        request
      )
  }
}
