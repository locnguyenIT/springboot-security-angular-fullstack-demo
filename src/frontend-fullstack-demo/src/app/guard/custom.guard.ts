import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { JwtUser } from '../interface/jwtUser';
import { JwtService } from '../service/jwt.service';

@Injectable({
  providedIn: 'root'
})
export class CustomGuard implements CanActivateChild {

  constructor(
    private jwtService: JwtService,
    private router: Router,
  ) {}
  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if(this.jwtService.getToken() !== null) //check token
    {
        return true;
    }
    else{
      this.router.navigateByUrl('index/login')
      return false;
    }
  }
  
}
