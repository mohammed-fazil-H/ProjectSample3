package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Plans;
import com.example.demo.entity.Register;

public interface PlansRepo extends JpaRepository<Plans, String>{
	List<Plans> findAllByPlanName(String searchKey);
	
	
	

}
