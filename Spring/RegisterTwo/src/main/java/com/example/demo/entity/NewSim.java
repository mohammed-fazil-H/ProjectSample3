package com.example.demo.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Component
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewSim {
	
	
	private String firstName;
	private String lastName;
	
	@Id
	private String emailId;
	private String gender;
	private String simNumber;
	private String phoneNumber;
	private String status;
	private String dob;
	private String trackingId;
	private String address;
	
	

}
