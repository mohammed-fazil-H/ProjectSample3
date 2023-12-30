import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgOtpInputModule } from 'ng-otp-input';
import { CustomerRoutingModule } from './customer-routing.module';
import { HomePageComponent } from './home-page/home-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterComponent } from './register/register.component';
import { PlansComponent } from './plans/plans.component';
import { PayMethodComponent } from './pay-method/pay-method.component';
import { OtpComponent } from './otp/otp.component';
import { NewSimComponent } from './new-sim/new-sim.component';
import { NavbarComponent } from './navbar/navbar.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { UsernavbarComponent } from './usernavbar/usernavbar.component';
import { MyProfileComponent } from './my-profile/my-profile.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    HomePageComponent,
    LoginPageComponent,
    RegisterComponent,
    PlansComponent,
    PayMethodComponent,
    OtpComponent,
    NewSimComponent,
    NavbarComponent,
    UserHomeComponent,
    UsernavbarComponent,
    MyProfileComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    NgOtpInputModule ,FormsModule
  ],
  exports: [
    HomePageComponent,NavbarComponent,OtpComponent
  ]
})
export class CustomerModule { }
