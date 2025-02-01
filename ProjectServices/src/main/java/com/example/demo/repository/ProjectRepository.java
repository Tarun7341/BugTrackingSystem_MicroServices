package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Project;
//import com.example.demo.model.Ticket;
//import com.example.demo.model.User;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

	List<Project> findProjectsByUserId(Integer userId);

}
