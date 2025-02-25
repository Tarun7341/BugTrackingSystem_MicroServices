package com.user.demo.service;

import com.user.demo.dto.Ticket;

public interface TesterService {

    void createTicket(Ticket ticket);

    Ticket getTicket(Integer id);

    void updateTicket(Integer id, Ticket ticket);

    void deleteTicket(Integer id);
}
