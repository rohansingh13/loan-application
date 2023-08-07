import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { CustomerViewComponent } from './components/customer-view/customer-view.component';
import { AdviserViewComponent } from './components/adviser-view/adviser-view.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },

  { path: '', redirectTo: 'login', pathMatch: 'full' },

  // Customer View Route
  { path: 'customer', component: CustomerViewComponent, canActivate: [AuthGuard] },

  // Adviser View Route
  { path: 'adviser', component: AdviserViewComponent, canActivate: [AuthGuard] },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
