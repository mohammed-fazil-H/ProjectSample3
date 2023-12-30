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
  
user : NewSim = new NewSim('','','','','',1);
  simAddress : string [] = [];
  simData: string[] = []; // Initialize an empty array to store addresses
  currentAddress: string = '';
 
 
  constructor(private uservice : UserServiceService){}

 
  addSim() {
    if (this.user.simCount < 4) {
      const newAddress = prompt('Enter the address for this sim card:'); // Prompt the user for an address
      if (newAddress) {
        this.simData.push(newAddress); // Add the entered address to the array
        this.user.simCount++;
      }
    }
  }
     
   
  removeSim(index: number) {
      this.simData.splice(index, 1);
      this.user.simCount--;
  }
 
  onSubmit() {
   
    console.log(this.user)
    this.simAddress = [];
    for (const address of this.simData) {
      if (address.trim() !== '') {
        this.simAddress.push(address);
      }
    }
   
    if (this.currentAddress.trim() !== '') {
      this.simAddress.push(this.currentAddress);
    }
   
   
    console.log(this.simAddress);
   
 
    this.uservice.submitUserData(this.user,this.simAddress).subscribe(
        (response) => {});

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