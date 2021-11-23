package com.mentoring.module2.controller;

import com.mentoring.module2.facade.BookingFacade;
import com.mentoring.module2.model.Ticket;
import com.mentoring.module2.model.impl.EventImpl;
import com.mentoring.module2.model.impl.UserImpl;
import com.mentoring.module2.util.GeneratePdfResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private BookingFacade bookingFacade;

    private static final SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

    @PostMapping
    public Ticket bookTicket(@RequestParam("userId") final long userId,
                             @RequestParam("eventId") final long eventId,
                             @RequestParam("place") final int place,
                             @RequestParam("category") final String category) {
        return bookingFacade.bookTicket(userId, eventId, place, Ticket.Category.valueOf(category));
    }

    @GetMapping(params = {"id", "name", "email", "size", "number"})
    public List<Ticket> getBookedTickets(final UserImpl user,
                                         @RequestParam("size") final int size,
                                         @RequestParam("number") final int number) {
        return bookingFacade.getBookedTickets(user, size, number);
    }

    @GetMapping(params = {"id", "name", "email", "size", "number"}, headers = {"accept=application/pdf"})
    public ResponseEntity<InputStreamResource> getBookedTicketsPdf(final UserImpl user,
                                                                   @RequestParam("size") final int size,
                                                                   @RequestParam("number") final int number) {
        final List<Ticket> tickets = bookingFacade.getBookedTickets(user, size, number);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(GeneratePdfResponse.convertUsersToPdf(tickets)));
    }

    @GetMapping(params = {"event", "size", "number"})
    public List<Ticket> getBookedTickets(@RequestParam("event") final EventImpl event,
                                         @RequestParam("size") final int size,
                                         @RequestParam("number") final int number) {
        return bookingFacade.getBookedTickets(event, size, number);
    }

    @DeleteMapping("/{id}")
    public boolean cancelTicket(@PathVariable("id") final long id) {
        return bookingFacade.cancelTicket(id);
    }

    @RequestMapping("/invokejob")
    public String handle() throws Exception {
        bookingFacade.preloadTickets();
        return "Batch job has been invoked";
    }
}