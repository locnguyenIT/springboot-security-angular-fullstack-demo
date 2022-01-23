import { HttpErrorResponse, HttpHeaderResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtUser } from 'src/app/interface/jwtUser';
import { JwtService } from 'src/app/service/jwt.service';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public jwtUser : JwtUser;
  public isLoginFailed = false;
  public errMessage : string;
  public isSignupFailed = false;

  constructor( private loginService: LoginService, private jwtService: JwtService, private router: Router) { }

  ngOnInit(): void {
  }

  public login(loginForm: NgForm):void
  {
      this.loginService.login(loginForm.value.username,loginForm.value.password).subscribe({
        next: (response: any) => {
          console.log(response);
          
          this.jwtService.saveToken(response.token); 
          this.jwtService.saveUsername(response.username); 
          console.log(response.token);
          console.log(response.username);
          alert('login sussessfully'); 
          this.router.navigate(['/menu/user'])
        },
        error: (err: HttpErrorResponse) => {
          this.errMessage = err.error.message;
          this.isLoginFailed = true;
        }
      }   
    )
  } 
  public registration(registrationForm: NgForm):void
  {
      this.loginService.registration(registrationForm.value).subscribe({
        next: (response : void) => {
          console.log(response);
          this.router.navigateByUrl('/index/login');
        },
        error: (err: HttpErrorResponse) =>{
          this.errMessage = err.error.message;
          this.isSignupFailed = true;
        },
        complete() {
          alert('registration sussessfully');
          
        } 
      }
    )
  }   
  // public login(loginForm: NgForm):void
  // {
  //     this.loginService.login(loginForm.value.username,loginForm.value.password).subscribe({
  //       next: (response: JwtUser) => {
  //         console.log(response);
  //         this.jwtUser = response;
  //         this.jwtService.saveToken(this.jwtUser.token); 
  //         this.jwtService.saveUsername(this.jwtUser.username); 
  //         this.jwtService.saveAuthority(this.jwtUser.authority); 
  //         alert(this.jwtUser.token);
  //         alert('login sussessfully'); 
  //         this.router.navigate(['/menu/user'])
  //       },
  //       error: () => {
  //         this.isLoginFailed = true;
  //       }
  //     }   
  //   )
  // }  

}
