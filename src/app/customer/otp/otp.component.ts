import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserServiceService } from 'src/app/Service/user-service.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-otp',
  templateUrl: './otp.component.html',
  styleUrls: ['./otp.component.css']
})
export class OtpComponent {
  title = 'otp-app';
  userEnteredOtp:string='';
  phonenumber:string=''
  otp: string='';



  constructor(private uService:UserServiceService , public router: Router){}


  inputDigitLeft: string = "Verify code";

  btnStatus: string = "btn-light";

 

  public configOptions = {

    length: 6,

    inputClass: 'digit-otp',

    containerClass: 'd-flex justify-content-between'

  }

 

  var1=false

  var2=true

  onOtp(){

    this.var1=true

    this.var2=false

  }

  onOtpChange(event: any) {
    this.userEnteredOtp = event;

    //this.otp = event;

    if(this.otp.length < this.configOptions.length) {

      this.inputDigitLeft = this.configOptions.length - this.otp.length + " digits Left";

      this.btnStatus = 'btn-light';

    }

    if(this.otp.length == this.configOptions.length) {

      this.inputDigitLeft = "Let's go!";

      this.btnStatus = 'btn-primary';

    }

  }
  

 

  validateOtp() {

 console.log(this.userEnteredOtp)

    const otpAndPhoneNumber = {

      otp : this.userEnteredOtp,

      phoneNumber: this.uService.phonenumber

    };

    console.log(otpAndPhoneNumber)

    this.uService.validateOtp(otpAndPhoneNumber).subscribe();
    this.uService.validateOtp(otpAndPhoneNumber).subscribe((valid) => {

      console.log('Response:', valid); // Log the valid value here

      if (valid) {

        this.redirectUserHomePage();

      } else {

        this.showInavlid();

      }

    });

 

}
redirectUserHomePage()

{

  this.router.navigate(['/UserHomepage']);

}

 

showInavlid()

{

  Swal.fire({

    icon: 'error',

    title: 'Invalid OTP',

    text: 'The OTP entered is invalid. Please try again.'

  });

}
}
