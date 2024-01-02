package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.Repo.RegisterRepo;
import com.example.demo.entity.Register;


@Service
public class PlanService {
	
	 @Autowired
	 private RegisterRepo repo;

	    public void updatePlanStatusAndPrice(String phoneNumber, String newPlanPrice,String planValidity) {
	    
	    	System.out.println(phoneNumber + newPlanPrice);
	        Register plan = repo.findByPhoneNumber(phoneNumber);
	        System.out.println(plan);
	        if (plan != null) {
	            // Update the plan price and set the plan status to "Active"
	            plan.setPlan(newPlanPrice);
	            plan.setPlanValidity(planValidity);
	            plan.setPlanStatus("Active");
	            repo.save(plan);
	        }
	    }

}
