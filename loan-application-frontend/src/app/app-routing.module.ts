import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { CustomerViewComponent } from './components/customer-view/customer-view.component';
import { AdviserViewComponent } from './components/adviser-view/adviser-view.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';
import { ForbiddenComponent } from './components/forbidden/forbidden.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  
  { path: 'register', component: RegisterComponent },

  { path: 'home', component: HomeComponent },

  { path: '', redirectTo: 'home', pathMatch: 'full' },

  // Customer View Route
  { path: 'customer', component: CustomerViewComponent, canActivate: [AuthGuard], data: { role: 'ROLE_USER' } },

  // Adviser View Route
  { path: 'adviser', component: AdviserViewComponent, canActivate: [AuthGuard], data: { role: 'ROLE_ADMIN' } },

  { path: 'forbidden', component: ForbiddenComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
