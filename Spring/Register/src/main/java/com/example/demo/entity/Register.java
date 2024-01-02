package com.example.demo.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Register {
	
	private String phoneNumber;
	private String firstName;
	private String lastName;
	
	@Id
	private String emailId;

	private String address;
	private String dob;
	private String plan;
	private String planStatus;
	private String planValidity;
	
	
//	@PrePersist
//	public void setdefault() {
//		this.planStatus="InActive";
//			
//	}
	
}
