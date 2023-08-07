import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerViewComponent } from './components/customer-view/customer-view.component';
import { AdviserViewComponent } from './components/adviser-view/adviser-view.component';

const routes: Routes = [
  { path: '', redirectTo: '/adviser', pathMatch: 'full' },

  // Customer View Route
  { path: 'customer', component: CustomerViewComponent },

  // Adviser View Route
  { path: 'adviser', component: AdviserViewComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
