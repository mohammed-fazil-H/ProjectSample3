package com.example.demo.Controller;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repo.NewSimRepo;
import com.example.demo.Repo.PlansRepo;
import com.example.demo.Repo.RegisterRepo;
import com.example.demo.Service.EmailService;
import com.example.demo.Service.NewSimService;
import com.example.demo.Service.PlanService;
import com.example.demo.entity.Plans;
import com.example.demo.entity.Register;
import com.example.demo.entity.NewSim;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
public class RegisterController {
	@Autowired
	RegisterRepo repo;
	
	@Autowired
	PlansRepo planRepo;
	
	@Autowired
	NewSim newSim;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	private NewSimService simSer;

	
	@Autowired
	NewSimRepo simRepo;
	
	@Autowired
	PlanService planService;
	
	@CrossOrigin(origins ="http://localhost:4200")
	@PostMapping("/register")
	@ResponseBody
	public String addUser(@RequestBody Register p) 
	{
		System.out.println(p);
		p.setPlanStatus("InActive");
		repo.save(p);
		return "Register Sucessfull";
	}
	


	@GetMapping("/details/{phoneNumber}")

		    public ResponseEntity<Register> getUserDetails(@PathVariable String phoneNumber) {

		        // Assuming you have a UserRepository to fetch user details

		        Register user = repo. findByPhoneNumber(phoneNumber);

		        if (user != null) {

		            return ResponseEntity.ok(user);

		        } else {

		            return ResponseEntity.notFound().build();

		        }

		    }
	@GetMapping("/plans")
	@ResponseBody

    public List<Plans> getPlans() {
		
		List plans = planRepo.findAll();
		System.out.println(plans);
		return plans;
        
	}
	
	

	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/plans/{searchKey}")
	public ResponseEntity<List<Plans>> getUserPlan(@PathVariable String searchKey) {

	    // Assuming you have a UserRepository to fetch user details
		System.out.println(searchKey);
	    List<Plans> user = planRepo.findAllByPlanName(searchKey);
	    System.out.println(user);

	    if (user != null) {

	        return ResponseEntity.ok(user);

	    } else {

	        return ResponseEntity.notFound().build();

	    }
	}
//	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/newSim")
	@ResponseBody
	public String NewSim(@RequestBody NewSim n) 
	{
		System.out.println(n);
		System.out.println(n.getFirstName());
		simSer.addUserSim(n);
//		simRepo.save(n);
		return "Request Sucessfull";
	}
	
	@CrossOrigin(origins ="http://localhost:4200")
	 @PutMapping("/activateSim/{phoneNumber}")
	    public ResponseEntity<String> activateUser(@PathVariable String phoneNumber) {
	        // Retrieve the user by phone number from the database
	        NewSim user = simRepo.findByPhoneNumber(phoneNumber);

	        if (user == null) {
	            return ResponseEntity.notFound().build();
	        }

	        // Update the user's status to "Active"
	        user.setStatus("Active");
	        simRepo.save(user);

	        return ResponseEntity.ok("User status updated to Active");
	    }
	
	@CrossOrigin(origins ="http://localhost:4200")
	@PutMapping("/updatePlan/{phoneNumber}/{plan}/{planValidity}")
    public ResponseEntity<String> updatePlan(@PathVariable String phoneNumber, @PathVariable String plan,@PathVariable String planValidity ) {
//		System.out.println(request);
//        String phoneNumber = (String) request.get("phoneNumber");
//        String newPlanPrice = (String) request.get("plan");
        System.out.println(phoneNumber+plan+planValidity);
        planService.updatePlanStatusAndPrice(phoneNumber, plan,planValidity);
        return ResponseEntity.ok("Plan status and price updated successfully");
    }
	
	@CrossOrigin(origins="http://localhost:4200/")
    @PostMapping("/generateBill")
	 public ResponseEntity<String> generateBill(@RequestBody String emailAddress) {

	        try {
	        	System.out.println(emailAddress);

               
	        	emailService.generateBillByEmail(emailAddress);

//	            String userName = "John Doe"; // Replace with the actual user name

	            String result = emailService.sendMail(emailAddress);
	            
	            


	            return ResponseEntity.ok("Bill generated and email sent successfully: " + result);

	        } catch (Exception e) {
	        	
	        	System.out.println(e);

	            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Error generating bill and sending email");

	        }

	    }

}


