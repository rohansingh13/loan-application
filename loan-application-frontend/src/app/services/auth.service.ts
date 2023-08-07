import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:9090'; 
  private jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/rest/auth/login`, { username, password }).pipe(
      tap(response => this.setToken(response.token))
    );
  }

  isLoggedIn(): boolean {
    const token = this.getToken();
    if (typeof token === 'string') {
      return !this.jwtHelper.isTokenExpired(token);
    }
    return false;
  }

  setToken(token: string): void {
    localStorage.setItem('jwt_token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('jwt_token');
  }

  logout(): void {
    localStorage.removeItem('jwt_token');
  }

  clear() {
    localStorage.clear();
  }
}
