package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SimCard {
	
	 @ManyToOne
	    @JoinColumn(name = "email", referencedColumnName = "emailid")
	    private User user; // This associates the SimCard with a User using the email ID
 
	    @Id
	    @Column(unique = true)
	    private String phonenumber;
	    private String simcardnumber;
	    private String location;
	    private String status;
	    
 
 
}
