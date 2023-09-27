import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/models/login-request';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginRequest: LoginRequest = { username: '', password: '' };

  constructor(
    private authService: AuthService,
    private tokenStorageService: TokenStorageService,    
    private router: Router
    ) { }

  ngOnInit(): void {
  }  

  onSubmit(): void {  
    this.authService.login(this.loginRequest)
      .subscribe(
        (response: any) => {
          console.log('Logged in successfully');       
          this.tokenStorageService.setToken(response.jwtToken);
          this.tokenStorageService.setRole(response.user.role);
          const role = response.user.role;
          if (role === 'ROLE_ADMIN') {
            this.router.navigate(['/adviser']);
          } else {
            this.router.navigate(['/customer']);
          }        
      },
      (error) => {
        console.error('Login failed', error);
      }    
    );
  }

}
