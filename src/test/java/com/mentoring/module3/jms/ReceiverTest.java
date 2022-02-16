package com.mentoring.module3.jms;

import com.mentoring.module3.dto.TicketDto;
import com.mentoring.module3.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReceiverTest {

    @Mock
    private TicketService ticketService;

    @Mock
    private TicketDto ticketDto;

    @InjectMocks
    private Receiver receiver;

    @Test
    void receiveMessage() {
        receiver.receiveMessage(ticketDto);
        verify(ticketService, times(1)).bookTicket(ticketDto);
    }
}