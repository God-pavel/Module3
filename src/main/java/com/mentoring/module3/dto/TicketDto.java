package com.mentoring.module3.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ticket")
public class TicketDto {

    private long userId;
    private long eventId;
    private String category;
    private int place;
    private long price;

    public TicketDto(long userId, long eventId, String category, int place, long price) {
        this.userId = userId;
        this.eventId = eventId;
        this.category = category;
        this.place = place;
        this.price = price;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TicketDto{" +
                "userId=" + userId +
                ", eventId=" + eventId +
                ", category='" + category + '\'' +
                ", place=" + place +
                ", price=" + price +
                '}';
    }
}
