package com.mentoring.module2.dao.impl;

import com.mentoring.module2.dao.EventDAO;
import com.mentoring.module2.model.Event;
import com.mentoring.module2.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;
import java.util.Random;

@Component
public class EventDAOImpl implements EventDAO {

    @Autowired
    private Storage storage;

    private static final Random random = new Random();

    @Override
    public Collection<Event> getAll() {
        return storage.getEvents().values();
    }

    @Override
    public Event getEventById(final long eventId) {
        return storage.getEvents().get(String.valueOf(eventId));
    }

    @Override
    public Event addEvent(final Event event) {
        long eventId = random.nextLong();

        while (getEventById(eventId) != null) {
            eventId = random.nextLong();
        }

        event.setId(eventId);
        storage.getEvents().put(String.valueOf(event.getId()), event);

        return event;
    }

    @Override
    public Event updateEvent(final Event event) {
        return storage.getEvents().computeIfPresent(String.valueOf(event.getId()), (k, v) -> event);
    }

    @Override
    public boolean deleteEvent(final long eventId) {
        return Objects.nonNull(storage.getEvents().remove(String.valueOf(eventId)));
    }
}
