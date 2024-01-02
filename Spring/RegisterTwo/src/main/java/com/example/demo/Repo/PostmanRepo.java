package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PostMan;

public interface PostmanRepo extends JpaRepository<PostMan, String> {
	

}
