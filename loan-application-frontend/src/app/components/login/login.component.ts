import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username: string = '';
  password: string = '';;

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router
    ) { }

  ngOnInit(): void {
  }  

  onSubmit(): void {
    this.userService.login(this.username, this.password).subscribe(
      (response: any) => {
        //this.userAuthService.setRoles(response.user.role);
        this.authService.setToken(response.token);
        this.router.navigate(['/adviser']);       
      },
      (error) => {
        console.log(error);
      }
    
    );
  }

}
