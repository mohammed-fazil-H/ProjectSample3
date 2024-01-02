package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repo.NewSimRepo;
import com.example.demo.Repo.PostmanRepo;
import com.example.demo.Repo.phoneNumberRepo;
import com.example.demo.entity.NewSim;
import com.example.demo.entity.PhoneNumber;
import com.example.demo.entity.PostMan;
import com.twilio.rest.chat.v1.service.User;

import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class NewSimService {
	
	@Autowired
	private NewSimRepo nrepo;
	@Autowired
	private phoneNumberRepo phRepo;
	
	@Autowired
	private PostmanRepo postRepo;
	
	public String addUserSim(NewSim user) {

		
		List<PhoneNumber> newPhList=phRepo.findByUnReservedStatusNativeQuery();
		System.out.println(newPhList.size());
		PhoneNumber n=newPhList.get(0);
		System.out.println(n);
		user.setTrackingId(n.getTrackingId());
//		user.setSimNumber(String.valueOf(n.getIccId()));
		nrepo.save(user);
		n.setReserved("Reserved");
		n.setDeliveredStatus("Pending");
		postRepo.save(new PostMan(user.getFirstName(),user.getAddress(),user.getEmailId(),n.getIccId(),n.getTrackingId(),"Pending"));
		phRepo.save(n);
		
		
		return "user created with sim";
	}
	
	

}
