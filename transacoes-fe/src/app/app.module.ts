import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { TransactionsComponent } from './transactions/transactions.component';
import { InsertTransactionsComponent } from './insert-transactions/insert-transactions.component';
import { CookieService } from 'ngx-cookie-service';
import { DATE_PIPE_DEFAULT_OPTIONS } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    TransactionsComponent,
    InsertTransactionsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [CookieService,
    {provide: DATE_PIPE_DEFAULT_OPTIONS, useValue: {timezone: '+0'}}],
  bootstrap: [AppComponent]
})
export class AppModule { }
