package com.mentoring.module3.jms;

import com.mentoring.module3.dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SampleJmsMessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final TicketDto ticket) {
        this.jmsTemplate.convertAndSend("mailbox", ticket);
    }
}
