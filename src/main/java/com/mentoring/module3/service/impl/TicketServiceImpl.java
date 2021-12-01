package com.mentoring.module3.service.impl;

import com.mentoring.module3.model.impl.Event;
import com.mentoring.module3.model.impl.Ticket;
import com.mentoring.module3.model.impl.User;
import com.mentoring.module3.repository.TicketDAO;
import com.mentoring.module3.service.EventService;
import com.mentoring.module3.service.TicketService;
import com.mentoring.module3.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

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
    @Transactional
    public Ticket bookTicket(final long userId,
                             final long eventId,
                             final int place,
                             final Ticket.Category category,
                             final BigDecimal price) {
        if (isPlaceBooked(place, eventId)) {
            LOGGER.warn("Place {} already booked", place);
            throw new IllegalStateException();
        }

        final User user = userService.getUserById(userId);
        final Event event = eventService.getEventById(eventId);

        if (!isEnoughMoney(user.getMoneyAmount(), price)) {
            LOGGER.warn("Not enough money");
            throw new IllegalStateException();
        }

        user.setMoneyAmount(user.getMoneyAmount().subtract(price));
        userService.updateUser(user);

        LOGGER.info("Booking ticket for {}", userId);
        return ticketDAO.save(Ticket.builder()
                .user(user)
                .event(event)
                .category(category)
                .place(place)
                .price(price)
                .build());
    }

    @Override
    public List<Ticket> getBookedTickets(final User user, final int pageSize, final int pageNum) {
        return ticketDAO.findAllByUser(user, PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "event.date")));
    }

    @Override
    public List<Ticket> getBookedTickets(final Event event, final int pageSize, final int pageNum) {
        return ticketDAO.findAllByEvent(event, PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "user.email")));
    }

    @Override
    public boolean cancelTicket(final long ticketId) {
        LOGGER.info("Canceling ticket with id {}", ticketId);
        try {
            ticketDAO.deleteById(ticketId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isPlaceBooked(final int place, final long eventId) {
        return ticketDAO.existsByEventIdAndPlace(eventId, place);
    }

    private boolean isEnoughMoney(final BigDecimal money, final BigDecimal price) {
        return money.subtract(price).longValue() >= 0;
    }
}
