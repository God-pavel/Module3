package com.mentoring.module3.service.impl;

import com.mentoring.module3.dto.EventDto;
import com.mentoring.module3.model.impl.Event;
import com.mentoring.module3.repository.EventDAO;
import com.mentoring.module3.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDAO eventDAO;

    private static final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    private static Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    @Override
    public Event getEventById(final long eventId) {
        return eventDAO.findById(eventId).orElse(null);
    }

    @Override
    public List<Event> getEventsByTitle(final String title, final int pageSize, final int pageNum) {
        return eventDAO.findAllByTitleContains(title, PageRequest.of(pageNum, pageSize));
    }

    @Override
    public List<Event> getEventsForDay(final String day, final int pageSize, final int pageNum) {
        try {
            return eventDAO.findAllByDate(format.parse(day), PageRequest.of(pageNum, pageSize));
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public Event createEvent(final EventDto event) {
        LOGGER.info("Creating event {}", event.getTitle());
        try {
            return eventDAO.save(convertToEvent(event));
        } catch (ParseException e) {
            LOGGER.warn("Date conversion failed event {}", event.getTitle());
            return null;
        }
    }

    @Override
    public Event updateEvent(final EventDto event) {
        LOGGER.info("Updating event with id {}", event.getId());
        final Event updatedEvent = eventDAO.findById(event.getId()).orElseThrow(IllegalArgumentException::new);
        try {
            updatedEvent.setDate(format.parse(event.getDate()));
        } catch (ParseException e) {
            return null;
        }
        updatedEvent.setTitle(event.getTitle());
        return eventDAO.save(updatedEvent);
    }

    @Override
    public boolean deleteEvent(final long eventId) {
        LOGGER.info("Deleting event with id {}", eventId);
        try {
            eventDAO.deleteById(eventId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private Event convertToEvent(final EventDto eventDto) throws ParseException {
        return Event.builder()
                .id(eventDto.getId())
                .date(format.parse(eventDto.getDate()))
                .title(eventDto.getTitle())
                .build();

    }
}
