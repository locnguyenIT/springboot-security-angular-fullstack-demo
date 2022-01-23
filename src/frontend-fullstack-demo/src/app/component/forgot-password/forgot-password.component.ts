import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ForgotPasswordService } from 'src/app/service/forgot-password.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  public isEmaiValid = false;
  public isEmaiInvalid = false;
  public errorMessage: string;
  constructor(private forgotPasswordService: ForgotPasswordService) { }

  ngOnInit(): void {
  }

  public sendEmailToken(sendEmailForm: NgForm): void
  {
    this.forgotPasswordService.sendEmailToken(sendEmailForm.value.email).subscribe(
      {
        next: (response: void) => {
          console.log(response);
          this.isEmaiValid = true;
        },
        error: (err: HttpErrorResponse) => {
          this.errorMessage = err.error.message;
          this.isEmaiInvalid = true;
        }   
      }
    )
  
  }

}
