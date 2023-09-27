import { Component, OnInit } from '@angular/core';
import { LoginRequest } from 'src/app/models/login-request';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  form: any = {
    username: null,   
    password: null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  signupForm: LoginRequest = {
    username: '',
    password: ''
  };

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }  

  register(): void {
    this.authService.register(this.signupForm)
      .subscribe(
        (response: any) => {
          console.log('Sign up successfully. User is created');
          console.log(response);
          this.isSuccessful = true;
          this.isSignUpFailed = false;
          //this.userAuthService.setRoles(response.user.role);
          //this.authService.setToken(response.token);
          //this.router.navigate(['/adviser']);       
        },
        (error) => {  
          console.error('Signup failed', error);
          this.errorMessage = error.error.message;
          this.isSignUpFailed = true;
        }
      );
  }

}
