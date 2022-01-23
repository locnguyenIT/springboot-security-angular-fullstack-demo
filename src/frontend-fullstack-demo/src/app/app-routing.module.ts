import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import { IndexComponent } from './component/index/index.component';
import { LoginComponent } from './component/login/login.component';
import { MenuComponent } from './component/menu/menu.component';
import { ResetPasswordComponent } from './component/reset-password/reset-password.component';
import { StudentComponent } from './component/student/student.component';
import { UserComponent } from './component/user/user.component';
import { CustomGuard } from './guard/custom.guard';

const routes: Routes = [
  {path: '',   redirectTo: '/index/login', pathMatch: 'full' }, // router level 1: /''
  
  {path: 'index',component: IndexComponent,
      children:[
        {path: 'login',component: LoginComponent},
        {path: 'forgot-password',component: ForgotPasswordComponent},
        {path: 'reset-password',component: ResetPasswordComponent},
      ]}, // router level 1: /index
  {path: 'menu',component: MenuComponent, canActivateChild: [CustomGuard],
  children:[
    {path: 'student', component: StudentComponent,  }, // router level 1: /''  
    {path: 'user', component: UserComponent}, // router level 1: /''  
  ]}
 
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  // imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponent = [IndexComponent,LoginComponent,StudentComponent,UserComponent,MenuComponent,ForgotPasswordComponent,ResetPasswordComponent]
