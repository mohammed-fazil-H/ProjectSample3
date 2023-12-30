import { Component } from '@angular/core';
import { Register } from '../Model/Register';
import { UserServiceService } from 'src/app/Service/user-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user : Register = new Register('','','','','','');

      submitted: boolean = false;

 

      constructor(private uservice : UserServiceService){}

      onSubmit()

      {
        this.uservice.addUser(this.user).subscribe((reponse)=>{this.submitted=true});

        console.log(this.user);

        this.uservice.updateUserStatus(this.user.phoneNumber).subscribe(() => {
          console.log("User status updated to Active");
        });

        Swal.fire({
          title: 'Registration Success',
          icon: 'success',
          showCancelButton: true,
          confirmButtonText: 'Return to Home',
          cancelButtonText: 'Login your account',
        }).then((result) => {
          if (result.isConfirmed) {
            // Handle the "Return to Home" action
            window.location.href = ''; // Replace '/home' with the actual URL of your home page.
          } else if (result.dismiss === Swal.DismissReason.cancel) {
            // Handle the "Login your account" action
            window.location.href = '/Login'; // Replace '/login' with the actual URL of your login page.
          }
        });



      }

}
