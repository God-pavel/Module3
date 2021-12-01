package com.mentoring.module3.controller;

import com.mentoring.module3.dto.EventDto;
import com.mentoring.module3.dto.TicketDto;
import com.mentoring.module3.facade.BookingFacade;
import com.mentoring.module3.model.impl.Event;
import com.mentoring.module3.model.impl.Ticket;
import com.mentoring.module3.model.impl.User;
import com.mentoring.module3.util.GeneratePdfResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private BookingFacade bookingFacade;

    private static final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    @PostMapping
    public Ticket bookTicket(final TicketDto ticketDto) {
        return bookingFacade.bookTicket(ticketDto.getUserId(), ticketDto.getEventId(), ticketDto.getPlace(),
                Ticket.Category.valueOf(ticketDto.getCategory()), BigDecimal.valueOf(ticketDto.getPrice()));
    }

    @GetMapping(params = {"id", "name", "email", "size", "number"})
    public List<Ticket> getBookedTickets(final User user,
                                         @RequestParam("size") final int size,
                                         @RequestParam("number") final int number) {
        return bookingFacade.getBookedTickets(user, size, number);
    }

    @GetMapping(params = {"id", "name", "email", "size", "number"}, headers = {"accept=application/pdf"})
    public ResponseEntity<InputStreamResource> getBookedTicketsPdf(final User user,
                                                                   @RequestParam("size") final int size,
                                                                   @RequestParam("number") final int number) {
        final List<Ticket> tickets = bookingFacade.getBookedTickets(user, size, number);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(GeneratePdfResponse.convertUsersToPdf(tickets)));
    }

    @GetMapping(params = {"id", "title", "date", "size", "number"})
    public List<Ticket> getBookedTickets(final EventDto eventDto,
                                         @RequestParam("size") final int size,
                                         @RequestParam("number") final int number) throws Exception{
        return bookingFacade.getBookedTickets(Event.builder().id(eventDto.getId()).title(eventDto.getTitle()).date(format.parse(eventDto.getDate())).build(), size, number);
    }

    @DeleteMapping("/{id}")
    public boolean cancelTicket(@PathVariable("id") final long id) {
        return bookingFacade.cancelTicket(id);
    }
}