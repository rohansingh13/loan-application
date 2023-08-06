import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from './material.module';
import { ToastrModule } from 'ngx-toastr';
import { CustomerViewComponent } from './components/customer-view/customer-view.component';
import { AdviserViewComponent } from './components/adviser-view/adviser-view.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomerViewComponent,
    AdviserViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,    
    ReactiveFormsModule,
    MaterialModule,
    ToastrModule.forRoot(),
    HttpClientModule    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
