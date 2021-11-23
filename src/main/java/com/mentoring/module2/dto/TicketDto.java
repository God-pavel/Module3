package com.mentoring.module2.dto;

import com.mentoring.module2.model.Ticket;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ticket")
public class TicketDto {

    private long userId;
    private long eventId;
    private Ticket.Category category;
    private int place;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public Ticket.Category getCategory() {
        return category;
    }

    public void setCategory(Ticket.Category category) {
        this.category = category;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
