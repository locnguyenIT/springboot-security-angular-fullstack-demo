import { HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtUser } from '../interface/jwtUser';
import { Registration } from '../interface/registration';



@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private URL_SERVER = 'http://localhost:8080'

  constructor(private http: HttpClient) { }

  // public login(username: string, password: string): Observable<HttpResponse<void>>
  // {
  //     return this.http.post<void>(`${this.URL_SERVER}`,{username,password},{ observe: 'response'});
  // }

  // public login(username: string, password: string): Observable<JwtUser>
  // {
  //     return this.http.post<JwtUser>(`${this.URL_SERVER}`,{username,password});
  // }
  public login(username: string, password: string): Observable<any>
  {
      return this.http.post<any>(`${this.URL_SERVER}/login`,{username,password});
  }
  public registration(registration: Registration): Observable<void>
  {
      return this.http.post<void>(`${this.URL_SERVER}/registration`,registration);
  }
}
