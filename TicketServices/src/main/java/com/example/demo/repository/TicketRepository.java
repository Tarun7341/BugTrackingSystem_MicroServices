package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	List<Ticket> findByStatusAndSeverity(String status, String sevirity);

	List<Ticket> findByProjectId(Integer id);

	List<Ticket> findByUserId(Integer id);
}
