package com.mentoring.module2.service.impl;

import com.mentoring.module2.dao.TicketDAO;
import com.mentoring.module2.model.Event;
import com.mentoring.module2.model.Ticket;
import com.mentoring.module2.model.User;
import com.mentoring.module2.service.EventService;
import com.mentoring.module2.service.TicketService;
import com.mentoring.module2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

    private static Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Override
    public Ticket bookTicket(final long userId,
                             final long eventId,
                             final int place,
                             final Ticket.Category category) {
        if (isPlaceBooked(place)) {
            LOGGER.warn("Place {} already booked", place);
            throw new IllegalStateException();
        }
        LOGGER.info("Booking ticket for {}", userId);
        return ticketDAO.createTicket(userId, eventId, place, category);
    }

    @Override
    public List<Ticket> getBookedTickets(final User user, final int pageSize, final int pageNum) {
        return ticketDAO.getAllTickets().stream()
                .filter(ticket -> ticket.getUserId() == user.getId())
//                .sorted(Comparator.comparing(ticket -> eventService.getEventById(ticket.getEventId()).getDate()))
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getBookedTickets(final Event event, final int pageSize, final int pageNum) {
        return ticketDAO.getAllTickets().stream()
                .filter(ticket -> ticket.getEventId() == event.getId())
                .sorted(Comparator.comparing(ticket -> userService.getUserById(ticket.getUserId()).getEmail()))
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public boolean cancelTicket(final long ticketId) {
        LOGGER.info("Canceling ticket with id {}", ticketId);
        return ticketDAO.deleteTicket(ticketId);
    }

    private boolean isPlaceBooked(int place) {
        return ticketDAO.getAllTickets().stream()
                .anyMatch(ticket -> ticket.getPlace() == place);
    }
}
