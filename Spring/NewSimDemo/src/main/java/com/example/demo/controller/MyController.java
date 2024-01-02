package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.SimCard;
import com.example.demo.Entity.User;
import com.example.demo.Repository.SimCardRepo;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class MyController {
	
	@Autowired
    private UserRepository userRepo;
    
//    @Autowired
//    private SimCardService simCardService;
 
    @Autowired
    private SimCardRepo simRepo;
    
//    @Autowired
//    private RechargePaymentRepo rechargeRepo;
    
    @Autowired
    private UserService uService ;
    
    
    @PostMapping("/newSimCard")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> createUser(@RequestBody Map<String, Object> requestData) {
        Map<String, Object> userMap = (Map<String, Object>) requestData.get("user");
        List<String> locations = (List<String>) requestData.get("location");
        System.out.println(userMap);
        System.out.println(locations);
        // Extract user data
        String emailid = (String) userMap.get("emailid");
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String dob = (String) userMap.get("dob");
        String gender = (String) userMap.get("gender");
        int simCount = (int) userMap.get("simCount");
 
        
        
        User user = new User();
        user.setEmailid(emailid);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDob(dob);
        user.setGender(gender);
        user.setSimCount(simCount);
        userRepo.save(user);
        
        
        for (String location : locations) {
            SimCard simCard = new SimCard();
            String phonenumber = uService.generateRandomPhoneNumber();
            String simnumber = uService.generateSimCardNumber();
            simCard.setPhonenumber(phonenumber);
            simCard.setSimcardnumber(simnumber);
            simCard.setUser(user);
            simCard.setLocation(location);
            simCard.setStatus("inactive");
            simRepo.save(simCard);
            uService.sendMail(emailid, phonenumber, simnumber, location);
        }
        
        
        return ResponseEntity.ok("{\"message\": \"added\"}");
    }
 

}
