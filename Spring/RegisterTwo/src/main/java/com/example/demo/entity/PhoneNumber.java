package com.example.demo.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PhoneNumber {
	
	private int phoneNumber; 
	@Id
	private String trackingId;
	private long iccId;
	private String reserved;
	private String deliveredStatus;

}
