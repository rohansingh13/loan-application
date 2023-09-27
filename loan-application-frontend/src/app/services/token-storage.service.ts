import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  public setRole(role: string): void {
    localStorage.setItem('role', role);
  }

  public getRole(): string | null {
    return localStorage.getItem('role');
  }

  public setToken(jwtToken: string): void {
    localStorage.setItem('jwtToken', jwtToken);
  }

  public getToken(): string {
    const token = localStorage.getItem('jwtToken');
    return token !== null ? token : '';
  }

  public logout(): void {
    localStorage.removeItem('jwtToken');
  }

  public clear() {
    localStorage.clear();
  }

  public isLoggedIn(){
    return this.getToken();
  }
}
