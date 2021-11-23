package com.mentoring.module2.controller;

import com.mentoring.module2.facade.BookingFacade;
import com.mentoring.module2.model.Event;
import com.mentoring.module2.model.impl.EventImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private BookingFacade bookingFacade;

    private static final SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

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
                                       @RequestParam("number") final int number) throws Exception {
        return bookingFacade.getEventsForDay(format.parse(date), size, number);
    }

    @PostMapping
    public Event createEvent(@RequestParam("title") final String title,
                             @RequestParam("date") final String date) throws Exception {
        return bookingFacade.createEvent(EventImpl.builder().title(title).date(format.parse(date)).build());
    }

    @PostMapping("/{id}")
    public Event updateEvent(@PathVariable("id") final long id,
                             @RequestParam("title") final String title,
                             @RequestParam("date") final String date) throws Exception {
        return bookingFacade.createEvent(EventImpl.builder().id(id).title(title).date(format.parse(date)).build());
    }

    @DeleteMapping("/{id}")
    public boolean deleteEvent(@PathVariable("id") final long id) {
        return bookingFacade.deleteEvent(id);
    }

    @ExceptionHandler()
    public String handleException() {
        return "error";
    }
}
