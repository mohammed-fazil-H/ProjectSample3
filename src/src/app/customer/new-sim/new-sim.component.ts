import { Component } from '@angular/core';
import { UserServiceService } from 'src/app/Service/user-service.service';
import { NewSim } from '../Model/NewSim';
import { Response } from '../Model/Response';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-new-sim',
  templateUrl: './new-sim.component.html',
  styleUrls: ['./new-sim.component.css']
})
export class NewSimComponent {
  //sims: number[] = [1]; // Initially, one set of SIM and Address fields
  email:string='';
  addresses: string[] = ['']; // Initially, one set of SIM and Address fields
  user:NewSim= new NewSim('','','','','','','','');
  submitted: any;


  constructor(public service : UserServiceService){}

  // sendMail(){
  //   console.log(this.email)
  //   this.service.sendM(this.email).subscribe()
  // }
 
  // addNewSim() {
  //   this.sims.push(1);
  //   this.addresses.push('');
  // }
  r:any;
  onSubmit()

  {
    console.log(this.user);
    console.log(this.user.emailId)
    this.service.sendM(this.user.emailId).subscribe((response)=>
    
    {
      this.r = response;
      console.log(this.r)
      this.user.phoneNumber = this.r.phoneNumber;
      this.user.simNumber = this.r.simNumber
      console.log("phn" + this.user.phoneNumber);
     console.log("sim" + this.user.simNumber);
     
     this.service.newSim(this.user).subscribe((response)=>
    {this.submitted=true});
    console.log(this.user);});

    Swal.fire({
      title: 'Sim Requested Successfully',
      icon: 'success',
      showCancelButton: true,
      confirmButtonText: 'Return to Home',
      cancelButtonText: 'Register your PhoneNumber',
    }).then((result) => {
      if (result.isConfirmed) {
        // Handle the "Return to Home" action
        window.location.href = ''; // Replace '/home' with the actual URL of your home page.
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        // Handle the "Login your account" action
        window.location.href = '/Register'; // Replace '/login' with the actual URL of your login page.
      }
    });

  }


  
}
