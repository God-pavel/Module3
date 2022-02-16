package com.mentoring.module3.service.impl;

import com.mentoring.module3.dto.TicketDto;
import com.mentoring.module3.model.impl.Event;
import com.mentoring.module3.model.impl.Ticket;
import com.mentoring.module3.model.impl.User;
import com.mentoring.module3.repository.TicketRepository;
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
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

    private static Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Override
    @Transactional
    public Ticket bookTicket(final TicketDto ticket) {
        if (isPlaceBooked(ticket.getPlace(), ticket.getEventId())) {
            LOGGER.warn("Place {} already booked", ticket.getPlace());
            throw new IllegalStateException();
        }

        final User user = userService.getUserById(ticket.getUserId());
        final Event event = eventService.getEventById(ticket.getEventId());

        if (!isEnoughMoney(user.getMoneyAmount(), BigDecimal.valueOf(ticket.getPrice()))) {
            LOGGER.warn("Not enough money");
            throw new IllegalStateException();
        }

        user.setMoneyAmount(user.getMoneyAmount().subtract(BigDecimal.valueOf(ticket.getPrice())));
        userService.updateUser(user);

        LOGGER.info("Booking ticket for {}", ticket.getUserId());
        return ticketRepository.save(Ticket.builder()
                .user(user)
                .event(event)
                .category(Ticket.Category.valueOf(ticket.getCategory()))
                .place(ticket.getPlace())
                .price(BigDecimal.valueOf(ticket.getPrice()))
                .build());
    }

    @Override
    public List<Ticket> getBookedTickets(final User user, final int pageSize, final int pageNum) {
        return ticketRepository.findAllByUser(user, PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "event.date")));
    }

    @Override
    public List<Ticket> getBookedTickets(final Event event, final int pageSize, final int pageNum) {
        return ticketRepository.findAllByEvent(event, PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "user.email")));
    }

    @Override
    public boolean cancelTicket(final long ticketId) {
        LOGGER.info("Canceling ticket with id {}", ticketId);
        try {
            ticketRepository.deleteById(ticketId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isPlaceBooked(final int place, final long eventId) {
        return ticketRepository.existsByEventIdAndPlace(eventId, place);
    }

    private boolean isEnoughMoney(final BigDecimal money, final BigDecimal price) {
        return money.subtract(price).longValue() >= 0;
    }
}
