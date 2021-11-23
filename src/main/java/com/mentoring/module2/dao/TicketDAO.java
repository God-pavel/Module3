package com.mentoring.module2.dao;

import com.mentoring.module2.model.Ticket;

import java.util.Collection;

public interface TicketDAO {
    Ticket createTicket(long userId, long eventId, int place, Ticket.Category category);
    
    Collection<Ticket> getAllTickets();
    
    boolean deleteTicket(long ticketId);
}
