import { Component } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-usernavbar',
  templateUrl: './usernavbar.component.html',
  styleUrls: ['./usernavbar.component.css']
})
export class UsernavbarComponent {
  constructor(private router:Router){}

  logout(){
    localStorage.removeItem("userdata");
    this.router.navigate(['']);
  }

}
