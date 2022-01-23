import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../interface/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private URL_SERVER = 'http://localhost:8080/api/user'

  constructor(private http: HttpClient) { }

  public getAllUser(): Observable<User[]>
  {
      return this.http.get<User[]>(`${this.URL_SERVER}`);
  }

  public getUser(username: string): Observable<User>
  {
      return this.http.get<User>(`${this.URL_SERVER}/get/${username}`);
  }

  public addUser(user: User, roleId: number): Observable<User>
  {
      return this.http.post<User>(`${this.URL_SERVER}/add/role/${roleId}`,user);
  }

  public deleteUser(userId: number): Observable<void>
  {
      return this.http.delete<void>(`${this.URL_SERVER}/delete/${userId}`);
  }

  public updateUser(userId: number, name: string, email: string, active : boolean): Observable<void>
  {
    const params= new HttpParams().set('name',name).set('email',email).set('isActive',active);
      return this.http.put<void>(`${this.URL_SERVER}/update/${userId}`,params);
  }
}
