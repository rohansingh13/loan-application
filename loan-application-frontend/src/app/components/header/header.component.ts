import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router    
  ) { }

  ngOnInit(): void {
  }

  public isLoggedIn() {
    const isUserLoggedIn = this.authService.isLoggedIn();
    console.log('Is User Logged In:', isUserLoggedIn);
    return isUserLoggedIn; 
  }

  public logout() {
    this.authService.clear();
    this.router.navigate(['/home']);
  }

}
