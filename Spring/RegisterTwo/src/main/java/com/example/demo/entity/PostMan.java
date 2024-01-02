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
public class PostMan {
	
	private String firstName;
	private String address;
	private String email;
	private long iccId;
	@Id
	private String trackingId;
	private String status;
	

}
