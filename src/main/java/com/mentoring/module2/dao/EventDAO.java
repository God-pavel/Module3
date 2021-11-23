package com.mentoring.module2.dao;

import com.mentoring.module2.model.Event;

import java.util.Collection;

public interface EventDAO {

    Collection<Event> getAll();
    Event getEventById(long eventId);
    Event addEvent(Event event);
    Event updateEvent(Event event);
    boolean deleteEvent(long eventId);
}
