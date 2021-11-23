package com.mentoring.module2.config;

import com.mentoring.module2.dto.TicketDto;
import com.mentoring.module2.service.TicketService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Writer implements ItemWriter<TicketDto> {

    @Autowired
    private TicketService ticketService;

    @Override
    public void write(List<? extends TicketDto> tickets) {
        if (tickets.size() > 0) {
            for (TicketDto ticket : tickets) {
                ticketService.bookTicket(ticket.getUserId(), ticket.getEventId(), ticket.getPlace(), ticket.getCategory());
            }
        }
    }
}