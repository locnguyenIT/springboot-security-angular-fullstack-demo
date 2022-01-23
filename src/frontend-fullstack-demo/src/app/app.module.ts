import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule, routingComponent } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationService } from './service/registration.service';
import { StudentService } from './service/student.service';
import { LoginService } from './service/login.service';
import { JwtService } from './service/jwt.service';
import { httpInterceptorProviders } from './interceptor/custom.interceptor';
import { UserService } from './service/user.service';
import { CustomGuard } from './guard/custom.guard';
import { ForgotPasswordService } from './service/forgot-password.service';


@NgModule({
  declarations: [
    AppComponent,
    routingComponent,
  ],
  imports: [
    BrowserModule, 
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [StudentService,LoginService,JwtService,UserService,ForgotPasswordService,CustomGuard,httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
