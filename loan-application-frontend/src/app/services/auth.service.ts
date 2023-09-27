import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { LoginResponse } from '../models/login-response';
import { LoginRequest } from '../models/login-request';
import { SignupRequest } from '../models/signup-request';
import { TokenStorageService } from './token-storage.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService { 

  private baseUrl = 'http://localhost:3040/rest/auth';

  constructor(
    private httpClient: HttpClient,
    private tokenStorageService: TokenStorageService
    ) { }

  public login(loginRequest: LoginRequest): Observable<LoginResponse> {   
    return this.httpClient.post<LoginResponse>(`${this.baseUrl}/login`, loginRequest, httpOptions)
      .pipe(
        map(response => {
          console.log(response);          
          return response;
        }),
        catchError(error => {
          console.log(error);
          throw error;
        })
      );
  } 

  register(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.httpClient.post<LoginResponse>(`${this.baseUrl}/signup`, loginRequest, httpOptions)
      .pipe(
        map(response => {
          // Save token in localStorage or cookie
          localStorage.setItem('token', response.token);
          return response;
        }),
        catchError(error => {
          throw error;
        })
      );
  } 

  public roleMatch(allowedRole: string): boolean {
    let isMatch = false;
    const userRole: any = this.tokenStorageService.getRole();

    if (userRole != null && userRole) {
      if (userRole === allowedRole) {
        isMatch = true;
      }
    }

    return isMatch;
  }
  
}
