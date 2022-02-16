package com.mentoring.module3.controller;

import com.mentoring.module3.dto.EventDto;
import com.mentoring.module3.facade.BookingFacade;
import com.mentoring.module3.model.impl.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable("id") final long id) {
        return bookingFacade.getEventById(id);
    }

    @GetMapping(params = {"title", "size", "number"})
    public List<Event> getEventsByTitle(@RequestParam("title") final String title,
                                        @RequestParam("size") final int size,
                                        @RequestParam("number") final int number) {
        return bookingFacade.getEventsByTitle(title, size, number);
    }

    @GetMapping(params = {"date", "size", "number"})
    public List<Event> getEventsForDay(@RequestParam("date") final String date,
                                       @RequestParam("size") final int size,
                                       @RequestParam("number") final int number) {
        return bookingFacade.getEventsForDay(date, size, number);
    }

    @PostMapping
    public Event createEvent(final EventDto event) {
        return bookingFacade.createEvent(event);
    }

    @PutMapping("/{id}")
    public Event updateEvent(final EventDto event) {
        return bookingFacade.updateEvent(event);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEvent(@PathVariable("id") final long id) {
        return bookingFacade.deleteEvent(id);
    }
}
