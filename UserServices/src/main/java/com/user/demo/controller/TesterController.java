package com.user.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.demo.dto.Ticket;
import com.user.demo.serviceImpl.TesterServiceImpl;

@RestController
@RequestMapping("/api/tester")
public class TesterController {

    private final TesterServiceImpl testerService;

    public TesterController(TesterServiceImpl testerService) {
        this.testerService = testerService;
    }

    @PostMapping("/tickets")
    public String createTicket(@RequestBody Ticket ticket) {
        testerService.createTicket(ticket);
        return "Ticket created successfully";
    }

    @GetMapping("/tickets/{id}")
    public Ticket getTicket(@PathVariable Integer id) {
        return testerService.getTicket(id);
    }

    @PutMapping("/tickets/{id}")
    public String updateTicket(@PathVariable Integer id, @RequestBody Ticket ticket) {
        testerService.updateTicket(id, ticket);
        return "Ticket updated successfully";
    }

    @DeleteMapping("/tickets/{id}")
    public String deleteTicket(@PathVariable Integer id) {
        testerService.deleteTicket(id);
        return "Ticket deleted successfully";
    }
}

