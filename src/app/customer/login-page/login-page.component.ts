import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SharedsourceService } from 'src/app/Service/sharedsource.service';

import { UserServiceService } from 'src/app/Service/user-service.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {

  phoneNumber: any;


  constructor(private uService : UserServiceService,private router:Router, private sservice:SharedsourceService) { }

  generateOtp() {

  if (this.phoneNumber) {

    console.log(this.phoneNumber);

    this.uService.generateOtp(this.phoneNumber).subscribe();
    this.uService.phonenumber = this.phoneNumber
    this.sservice.setPhoneNumber(this.phoneNumber)
   
    this.router.navigate(['/otp']);


  }

  

}

}
 

  


