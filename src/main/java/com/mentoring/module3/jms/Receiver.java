package com.mentoring.module3.jms;

import com.mentoring.module3.dto.TicketDto;
import com.mentoring.module3.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    private TicketService ticketService;

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(final TicketDto ticketDto) {
        System.out.println("Received <" + ticketDto + ">");
        ticketService.bookTicket(ticketDto);
        System.out.println("finished");
    }
}
