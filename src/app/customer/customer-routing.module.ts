import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterComponent } from './register/register.component';
import { NewSimComponent } from './new-sim/new-sim.component';
import { HomePageComponent } from './home-page/home-page.component';
import { OtpComponent } from './otp/otp.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { MyProfileComponent } from './my-profile/my-profile.component';
import { PlansComponent } from './plans/plans.component';
import { PayMethodComponent } from './pay-method/pay-method.component';

const routes: Routes = [
  { path: 'Login', component:LoginPageComponent},
  { path: 'Register', component:RegisterComponent},
  { path: 'NewSim', component:NewSimComponent},
  { path: 'generateotp', component:OtpComponent},
  { path: 'UserHomepage', component:UserHomeComponent},
  { path: 'MyProfile', component:MyProfileComponent},
  { path: 'Plans', component:PlansComponent},
  { path: '', component:HomePageComponent},
  { path: 'otp', component:OtpComponent},
  { path: 'payment', component:PayMethodComponent}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
