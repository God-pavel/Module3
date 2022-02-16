package com.mentoring.module3.jms;

import com.mentoring.module3.dto.TicketDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SampleJmsMessageSenderTest {

    @Mock
    private JmsTemplate jmsTemplate;

    @Mock
    private TicketDto ticketDto;

    @InjectMocks
    private SampleJmsMessageSender sampleJmsMessageSender;

    @Test
    void sendMessage() {
        sampleJmsMessageSender.sendMessage(ticketDto);
        verify(jmsTemplate, times(1)).convertAndSend("mailbox", ticketDto);
    }
}