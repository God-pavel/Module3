package com.mentoring.module2.facde.impl;

import com.mentoring.module2.facade.impl.BookingFacadeImpl;
import com.mentoring.module2.model.Event;
import com.mentoring.module2.model.Ticket;
import com.mentoring.module2.model.User;
import com.mentoring.module2.model.impl.EventImpl;
import com.mentoring.module2.model.impl.UserImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookingFacadeImplTest {

    @Autowired
    private BookingFacadeImpl bookingFacade;

    @Test
    void realLifeScenario() {
        final User newUser = bookingFacade.createUser(UserImpl.builder().name("David").email("email").build());

        assertEquals("David", bookingFacade.getUserByEmail("email").getName());

        final Event newEvent = bookingFacade.createEvent(EventImpl.builder().title("NewEvent").date(Date.from(Instant.now())).build());

        assertEquals(1, bookingFacade.getEventsByTitle("NewEvent", 1, 1).size());

        final Ticket newTicket = bookingFacade.bookTicket(newUser.getId(), newEvent.getId(), 11, Ticket.Category.BAR);

        assertEquals(bookingFacade.getBookedTickets(newEvent, 1, 1), bookingFacade.getBookedTickets(newUser, 1, 1));

        bookingFacade.cancelTicket(newTicket.getId());

        assertEquals(Collections.EMPTY_LIST, bookingFacade.getBookedTickets(newUser, 1, 1));
        assertEquals(Collections.EMPTY_LIST, bookingFacade.getBookedTickets(newEvent, 1, 1));
    }
}