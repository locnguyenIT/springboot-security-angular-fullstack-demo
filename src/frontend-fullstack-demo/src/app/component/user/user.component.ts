import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/interface/user';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  listUser: User[] = [];
  isActive: boolean = false;
  user: User;
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getAllUser();
  }

  public getUser(user: User):void
  {
    this.user = user;
  }

  public getAllUser():void
  {
    this.userService.getAllUser().subscribe(
      {
          next: (response: User[]) => {
            this.listUser = response;
            console.log(this.listUser);
          },
          error: (err: HttpErrorResponse) => {
            alert(err.error.message);
          }
      }
    )
  }

  public addUser(addForm: NgForm):void
  {
    document.getElementById('add-student-close').click(); 
    this.userService.addUser(addForm.value,addForm.value.roleId).subscribe(
      {
          next: (response: User) => {
            this.user = response;
            console.log(this.user);
            this.getAllUser();
          },
          error: (err: HttpErrorResponse) => {
            alert(err.error.message);
          }
      }
    )
  }

  public deleteUser(userId: number):void
  {
    document.getElementById('btn-no-delete').click();
    this.userService.deleteUser(userId).subscribe(
      {
          next: (response: void) => {
            this.getAllUser()
          },
          error: (err: HttpErrorResponse) => {
            alert(err.error.message);
          }
      }
    )
  }

  public updateUser(updateForm: NgForm):void
  {
    document.getElementById('btn-edit-close').click();
    this.userService.updateUser(updateForm.value.id,
                                updateForm.value.name,
                                updateForm.value.email,
                                updateForm.value.active).subscribe(
      {
          next: (response: void) => {
            this.getAllUser()
          },
          error: (err: HttpErrorResponse) => {
            alert(err.error.message);
          }
      }
    )
  }

}
