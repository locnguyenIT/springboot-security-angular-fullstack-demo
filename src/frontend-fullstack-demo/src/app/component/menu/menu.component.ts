import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/interface/user';
import { JwtService } from 'src/app/service/jwt.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  public username: string;
  public user: User;

  constructor(private jwtService: JwtService, private userService: UserService,private router: Router) { }

  ngOnInit(): void {
    this.getUser();
  }

  public signOut():void
  {
    this.jwtService.clearToken();
    this.router.navigate(['/index/login']);
  }

  public getUser():void
  {
    this.username = this.jwtService.getUsername();
    this.userService.getUser(this.username).subscribe(
      {
        next: (response: User) => {
          this.user = response;
          this.jwtService.saveUser(this.user);
          console.log(this.jwtService.getUser());
          //console.log(this.user);
        },
        error: (err: HttpErrorResponse) => {
          alert(err.error.message);
        }
      }
    )
  
  }

}
