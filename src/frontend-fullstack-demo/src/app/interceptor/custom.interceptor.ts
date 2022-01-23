import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS,
  HttpErrorResponse
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { JwtService } from '../service/jwt.service';
import { Router } from '@angular/router';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class CustomInterceptor implements HttpInterceptor {

  constructor(private jwtService: JwtService, private router: Router) {}

  intercept(request: HttpRequest<void>, next: HttpHandler): Observable<HttpEvent<void>> {
    
    const token = this.jwtService.getToken();
    if(token !== null)
    {
      request = request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY,token)});
    }
     return next.handle(request);
     //.pipe(
    //   catchError(
    //     (err : HttpErrorResponse) => {
    //       console.log(err.status);
    //       if(err.status === 403){
    //         alert('You dont have enough permission');
    //       }
    //       throw new Error('Something was wrong');
    //       //  throwError(');
            
    //     }
    //   )
    // );

    
  }
}

export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: CustomInterceptor, multi: true }
];
