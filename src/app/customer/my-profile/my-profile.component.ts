import { Component } from '@angular/core';

import { UserServiceService } from '../../Service/user-service.service';
import { SharedsourceService } from '../../Service/sharedsource.service';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent {
  phoneNumber: string = '';
  userDetails:any;
  recievedData : any;


  constructor(private uService:UserServiceService,private sservice: SharedsourceService)
  {
    this.phoneNumber=this.sservice.getPhoneNumber();
    this.uService.getUserDetails(this.phoneNumber).subscribe((user:any)=>{
      this.userDetails=user
      console.log(this.userDetails);
      
      localStorage.setItem("userdata",JSON.stringify(user))
      this.recievedData = this.userDetails;

      
    });

  }
    

    ngOnInit(): void {

      const storedData = localStorage.getItem("userdata");

      if(storedData)

      {

        this.recievedData = JSON.parse(storedData);

      }
      console.log(this.recievedData);
      

    }

}







