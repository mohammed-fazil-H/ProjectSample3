import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Register } from '../customer/Model/Register';
import { Plans } from '../customer/Model/Plans';
import { NewSim } from '../customer/Model/NewSim';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  url : string ='http://localhost:8035'

  phonenumber : string = '';

 

  constructor(private httpClient:HttpClient) { }

  generateOtp(phoneNumber:string)

  {
    const body={phoneNumber}
    return this.httpClient.post("http://localhost:8035/requestotp",body)

  }
  sendM(mail:any):Observable<Response>{
    console.log(mail);
   
    return this.httpClient.post<Response>("http://localhost:8035/NewSim",mail)
  }

  addUser(newUser : Register)

  {

    return this.httpClient.post('http://localhost:8182/register',newUser);

  }
  validateOtp(otpAndPhoneNumber: any):Observable<any> {

    // Send the entered OTP and phone number to the server for validation

    //console.log(this.httpClient.post<boolean>('http://localhost:8035/validateotp', otpAndPhoneNumber));

    return this.httpClient.post<Boolean>('http://localhost:8035/validateotp', otpAndPhoneNumber);

 

  }
  getUserDetails(phoneNumber:string)
  {
    return this.httpClient.get<Register>(`http://localhost:8182/details/${phoneNumber}`);
  }


  getPlans()

  {

    return this.httpClient.get('http://localhost:8182/plans')

  }
  getUserPlan(searchKey:any)
  {
    return this.httpClient.get<Plans[]>(`http://localhost:8182/plans/${searchKey}`);
  }

  newSim(user:NewSim){
    
    return this.httpClient.post('http://localhost:8182/newSim',user);

  }

  updateUserStatus(phoneNumber: string): Observable<any> {9
    // Define the endpoint for updating user status
    const endpoint = `http://localhost:8182/activateSim/${phoneNumber}`;

    // Make an HTTP PUT request to update the user status
    return this.httpClient.put(endpoint, null);
  }

  updatePlan(phoneNumber: string, planPrice: string, planValidity:string): Observable<any> {
    // Define the endpoint for updating the plan
    const endpoint = `http://localhost:8182/updatePlan/${phoneNumber}/${planPrice}/${planValidity}`;
    
    

    // Prepare the request body with phoneNumber and new planPrice
    const body = { phoneNumber, planPrice,planValidity };
    console.log(body);
    

    // Make an HTTP PUT request to update the plan
    return this.httpClient.put(endpoint,0);
  }
  
  generateBill(emailId:string):Observable<any>{
    const endpoint = `http://localhost:8182/generateBill`;
    return this.httpClient.post(endpoint,emailId);

  }
  getUserInformationByEmail(){
    
  }
  submitUserData(user: NewSim , location : string[]): Observable<any> {
    const data = {
      user: user,
      location: location
    };
    return this.httpClient.post('http://localhost:9292/api/users/newSimCard', data);
  }

}
