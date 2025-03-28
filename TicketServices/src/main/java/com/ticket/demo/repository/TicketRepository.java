package com.ticket.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ticket.demo.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	List<Ticket> findByStatusAndSeverity(String status, String sevirity);

	List<Ticket> findByProjectId(Integer id);

	
}
