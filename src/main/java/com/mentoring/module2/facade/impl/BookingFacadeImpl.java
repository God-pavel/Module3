package com.mentoring.module2.facade.impl;

import com.mentoring.module2.facade.BookingFacade;
import com.mentoring.module2.model.Event;
import com.mentoring.module2.model.Ticket;
import com.mentoring.module2.model.User;
import com.mentoring.module2.service.EventService;
import com.mentoring.module2.service.TicketService;
import com.mentoring.module2.service.UserService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingFacadeImpl implements BookingFacade {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job processJob;

    private final EventService eventService;
    private final TicketService ticketService;
    private final UserService userService;

    public BookingFacadeImpl(final EventService eventService,
                             final TicketService ticketService,
                             final UserService userService) {
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.userService = userService;
    }


    @Override
    public Event getEventById(final long eventId) {
        return eventService.getEventById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(final String title, final int pageSize, final int pageNum) {
        return eventService.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(final Date day, final int pageSize, final int pageNum) {
        return eventService.getEventsForDay(day, pageSize, pageNum);
    }

    @Override
    public Event createEvent(final Event event) {
        return eventService.createEvent(event);
    }

    @Override
    public Event updateEvent(final Event event) {
        return eventService.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(final long eventId) {
        return eventService.deleteEvent(eventId);
    }

    @Override
    public User getUserById(final long userId) {
        return userService.getUserById(userId);
    }

    @Override
    public User getUserByEmail(final String email) {
        return userService.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(final String name, final int pageSize, final int pageNum) {
        return userService.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(final User user) {
        return userService.createUser(user);
    }

    @Override
    public User updateUser(final User user) {
        return userService.updateUser(user);
    }

    @Override
    public boolean deleteUser(final long userId) {
        return userService.deleteUser(userId);
    }

    @Override
    public Ticket bookTicket(final long userId,
                             final long eventId,
                             final int place,
                             final Ticket.Category category) {
        return ticketService.bookTicket(userId, eventId, place, category);
    }

    @Override
    public List<Ticket> getBookedTickets(final User user, final int pageSize, final int pageNum) {
        return ticketService.getBookedTickets(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(final Event event, final int pageSize, final int pageNum) {
        return ticketService.getBookedTickets(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(final long ticketId) {
        return ticketService.cancelTicket(ticketId);
    }

    @Override
    public void preloadTickets() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(processJob, jobParameters);
    }
}
