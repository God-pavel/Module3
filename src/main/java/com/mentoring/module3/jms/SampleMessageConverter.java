package com.mentoring.module3.jms;

import com.mentoring.module3.dto.TicketDto;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class SampleMessageConverter implements MessageConverter {

    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        TicketDto ticket = (TicketDto) object;
        MapMessage message = session.createMapMessage();
        message.setLong("userId", ticket.getUserId());
        message.setLong("eventId", ticket.getEventId());
        message.setString("category", ticket.getCategory());
        message.setInt("place", ticket.getPlace());
        message.setLong("price", ticket.getPrice());
        return message;
    }

    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        MapMessage mapMessage = (MapMessage) message;
        return new TicketDto(mapMessage.getLong("userId"),
                mapMessage.getLong("eventId"),
                mapMessage.getString("category"),
                mapMessage.getInt("place"),
                mapMessage.getLong("price"));
    }

}
