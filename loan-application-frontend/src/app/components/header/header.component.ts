import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {  

  constructor(
    public authService: AuthService,
    private tokenStorageService: TokenStorageService,
    private router: Router    
  ) { }

  ngOnInit(): void {
  }

  public isLoggedIn() {
    const isUserLoggedIn = this.tokenStorageService.isLoggedIn();   
    return isUserLoggedIn; 
  }

  public logout() {
    this.tokenStorageService.clear();
    this.router.navigate(['/home']);
  }

}
