import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { CustomerViewComponent } from './components/customer-view/customer-view.component';
import { AdviserViewComponent } from './components/adviser-view/adviser-view.component';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './guards/auth.guard';

@NgModule({
  declarations: [
    AppComponent,
    CustomerViewComponent,
    AdviserViewComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    HttpClientModule,
    FormsModule        
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
