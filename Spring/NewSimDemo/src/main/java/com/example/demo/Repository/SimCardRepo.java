package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.SimCard;

public interface SimCardRepo extends JpaRepository<SimCard,String> {

}
