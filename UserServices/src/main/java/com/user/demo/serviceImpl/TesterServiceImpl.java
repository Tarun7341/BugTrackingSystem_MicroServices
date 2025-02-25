package com.user.demo.serviceImpl;

import org.springframework.stereotype.Service;

import com.user.demo.client.TicketFeignClient;
import com.user.demo.dto.Ticket;
import com.user.demo.service.TesterService;

@Service
public class TesterServiceImpl implements TesterService {
	
	private final TicketFeignClient ticketFeignClient;

    public TesterServiceImpl(TicketFeignClient ticketFeignClient) {
        this.ticketFeignClient = ticketFeignClient;
    }

    @Override
    public void createTicket(Ticket ticket) {
        ticketFeignClient.createTicket(ticket);
    }

    @Override
    public Ticket getTicket(Integer id) {
        return ticketFeignClient.getTicket(id);
    }

    @Override
    public void updateTicket(Integer id, Ticket ticket) {
        ticketFeignClient.updateTicket(id, ticket);
    }

    @Override
    public void deleteTicket(Integer id) {
        ticketFeignClient.deleteTicket(id);
    }

}
