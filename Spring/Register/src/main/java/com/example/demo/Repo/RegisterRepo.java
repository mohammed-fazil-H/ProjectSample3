package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Register;

public interface RegisterRepo extends JpaRepository<Register ,String > {
	
	Register findByPhoneNumber(String phoneNumber);
	
	Register findByEmailId(String emailId);
	
	

}
