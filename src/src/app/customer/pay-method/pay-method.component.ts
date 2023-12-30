import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SharedsourceService } from 'src/app/Service/sharedsource.service';
import { UserServiceService } from 'src/app/Service/user-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-pay-method',
  templateUrl: './pay-method.component.html',
  styleUrls: ['./pay-method.component.css']
})
export class PayMethodComponent implements OnInit {

  planPrice:any;      // before : planPrice : number =0;
  planValidity:any;
  user: any={};
  emailId:any;
  recievedData:any;

  constructor(private route:ActivatedRoute,private uservice : UserServiceService) {
    this.route.queryParams.subscribe(params => {
      this.planPrice = params['price'];
      this.planValidity = params['validity']
      console.log(this.planPrice,this.planValidity);
    });
   
    
  }
  ngOnInit(): void {
    const storedData = localStorage.getItem("userdata");

    if(storedData)
  
    {
  
      this.recievedData = JSON.parse(storedData);
      console.log(this.recievedData);
      
  
    }
  
    
  }

  
  onSubmit(){
    console.log( this.planPrice);
    console.log(this.emailId);
    console.log(this.recievedData.phoneNumber);
    
    this.uservice.updatePlan(this.recievedData.phoneNumber, this.planPrice,this.planValidity).subscribe(() => {
      console.log("Plan status and price updated successfully");
    });

    this.uservice.generateBill(this.emailId).subscribe(() => {
      console.log("Generating Bill");
      
    })
    
    Swal.fire({
      title: 'Payment Success',
      icon: 'success',
      showCancelButton: true,
      confirmButtonText: 'Return to Home',
      //cancelButtonText: 'Login your account',
    }).then((result) => {
      if (result.isConfirmed) {
        // Handle the "Return to Home" action
        window.location.href = '/UserHomepage'; // Replace '/home' with the actual URL of your home page.
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        // Handle the "Login your account" action
        //window.location.href = '/Login'; // Replace '/login' with the actual URL of your login page.
      }
    });
  }

 onSubmit1(){

  Swal.fire({
    title: 'Payment Failed',
    icon: 'error',
    showCancelButton: true,
    confirmButtonText: 'Return to Home',
    //cancelButtonText: 'Login your account',
  }).then((result) => {
    if (result.isConfirmed) {
      // Handle the "Return to Home" action
      window.location.href = '/UserHomepage'; // Replace '/home' with the actual URL of your home page.
    } else if (result.dismiss === Swal.DismissReason.cancel) {
      // Handle the "Login your account" action
      //window.location.href = '/Login'; // Replace '/login' with the actual URL of your login page.
    }
  });


 }

  

}
