import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedsourceService {

  private phoneNumber: string = '';
  public planPrice: any;

  setPlanPrice(price: any) {
    this.planPrice = price;
  }

  getPlanPrice() {
    return this.planPrice;
  }

 

  setPhoneNumber(phoneNumber: string) {

    this.phoneNumber = phoneNumber;

  }

 

  getPhoneNumber(): string {

    return this.phoneNumber;

  }
}
