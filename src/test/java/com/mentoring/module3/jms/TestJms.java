package com.mentoring.module3.jms;

import com.mentoring.module3.Module3Application;
import com.mentoring.module3.dto.TicketDto;
import com.mentoring.module3.repository.EventRepository;
import com.mentoring.module3.repository.TicketRepository;
import com.mentoring.module3.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Module3Application.class, H2JpaConfig.class})
@Sql(scripts = "/create-data.sql")
class TestJms {

    @Autowired
    private SampleJmsMessageSender sender;

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    private final TicketDto ticketDto = new TicketDto(111, 111, "BAR", 1, 1L);

    @Test
    void test1() throws InterruptedException {
        sender.sendMessage(ticketDto);
        Thread.sleep(500);
        assertEquals(ticketDto.getPrice(), ticketRepository.findByPlaceAndEventId(1, 111).get().getPrice().longValue());
    }
}