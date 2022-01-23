import { Injectable } from '@angular/core';
import { User } from '../interface/user';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor() { }

  public saveToken(token: string): void
  {
    window.sessionStorage.setItem("Token",token);
  }

  public saveUser(user: User): void
  {
    window.sessionStorage.setItem("User",JSON.stringify(user));
  }

  public getUser(): User
  {
    return JSON.parse(window.sessionStorage.getItem("User"));
  }

  public saveUsername(username: string): void
  {
    window.sessionStorage.setItem("Username",username);
  }

  public getToken(): string
  {
    return window.sessionStorage.getItem("Token");
  }

  public getUsername(): string
  {
    return window.sessionStorage.getItem("Username");
  }

  public clearToken(): void
  {
    window.sessionStorage.clear();
  }
}
