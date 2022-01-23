import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Registration } from '../interface/registration';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private URL_SERVER = 'http://localhost:8080/registration'

  constructor(private http: HttpClient) { }

  public registration(registration: Registration): Observable<void>
  {
      return this.http.post<void>(`${this.URL_SERVER}`,registration);
  }
}
