import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ForgotPasswordService {

  private URL_SERVER = 'http://localhost:8080'

  constructor(private http: HttpClient) { }

  public sendEmailToken(email: string): Observable<void>
  {
      // const param= new HttpParams().set('email',email)
      return this.http.post<void>(`${this.URL_SERVER}/forgot-password/send-email-token/${email}`,null);
  }

  public resetPassword(token:string,password: string): Observable<void>
  {
      const param= new HttpParams().set('token',token).set('password',password)
      return this.http.put<void>(`${this.URL_SERVER}/forgot-password/reset-password`,param);
  }

}
