package com.mentoring.module3;

import com.mentoring.module3.dto.TicketDto;
import com.mentoring.module3.jms.SampleJmsMessageSender;
import com.mentoring.module3.model.impl.Ticket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Module3Application {

    public static void main(String[] args) throws Exception {
//        final ConfigurableApplicationContext context = SpringApplication.run(Module3Application.class, args);
//
//        final SampleJmsMessageSender sender = context.getBean(SampleJmsMessageSender.class);
//
//        System.out.println("Booking started.");
//
//        sender.sendMessage(new TicketDto(1, 1, Ticket.Category.PREMIUM.name(), (int) (Math.random() * 1000), 0));
//        sender.sendMessage(new TicketDto(1, 1, Ticket.Category.BAR.name(), (int) (Math.random() * 1000), 0));
//        sender.sendMessage(new TicketDto(1, 1, Ticket.Category.STANDARD.name(), (int) (Math.random() * 1000), 0));
//        sender.sendMessage(new TicketDto(1, 1, Ticket.Category.PREMIUM.name(), (int) (Math.random() * 1000), 0));
    }

}
