import { Component } from '@angular/core';
import { UserServiceService } from '../../Service/user-service.service';
import { SharedsourceService } from 'src/app/Service/sharedsource.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-plans',
  templateUrl: './plans.component.html',
  styleUrls: ['./plans.component.css']
})
export class PlansComponent {

  plans : any;
  searchKey:any;

  constructor(private uService : UserServiceService,private sservice:SharedsourceService,private router:Router){

   

    this.uService.getPlans().subscribe((planlist: any) => {

      this.plans = planlist;

      console.log(this.plans);
      

    });

  }
  Search()
    {

      if (this.searchKey) {
    
        console.log(this.searchKey);
    
        this.uService.getUserPlan(this.searchKey).subscribe(
          data=>{console.log(data),
          this.plans = data;

          this.sservice.setPlanPrice(this.plans.planPrice)
          console.log(this.plans.planPrice);
          
         
          
          }
        );
      }
    }

    buy(planPrice: string, planValidity: string) {
      this.router.navigate(['/payment'], {
        queryParams: {
          price: planPrice,
          validity: planValidity // Add planValidity to the queryParams
        }
      });
    }
}


