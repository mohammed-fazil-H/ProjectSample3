package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class User {
	 	@Id
	    private String emailid;
	    private String firstName;
	    private String lastName;
	    private String dob;
	    private String gender ;
	    private int simCount;
	    
 
}