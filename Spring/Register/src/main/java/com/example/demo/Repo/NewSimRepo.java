package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.NewSim;

public interface NewSimRepo extends JpaRepository<NewSim, String> {
	NewSim findByPhoneNumber(String phoneNumber);

}
