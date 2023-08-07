import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:9090'; 
  
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(
    private httpclient: HttpClient,
    private authService: AuthService
  ) { }

  public login(username: string, password: string): Observable<any> {
    return this.httpclient.post(`${this.apiUrl}/rest/auth/login`, { username, password }, {
      headers: this.requestHeader,
    });
  }
}
