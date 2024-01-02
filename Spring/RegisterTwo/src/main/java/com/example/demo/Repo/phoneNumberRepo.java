package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.PhoneNumber;

public interface phoneNumberRepo extends JpaRepository<PhoneNumber, String> {
	
//	@Query("select * from PhoneNumber where reserved =?1")
//	List<PhoneNumber> findByReserved(String val);
	 @Query(value = "SELECT * FROM phone_number WHERE reserved = 'UnReserved'", nativeQuery = true)
	 List<PhoneNumber> findByUnReservedStatusNativeQuery();
	 
	 

}
