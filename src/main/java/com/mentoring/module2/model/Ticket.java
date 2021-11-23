package com.mentoring.module2.model;

/**
 * Created by maksym_govorischev.
 */
public interface Ticket {
    public enum Category {STANDARD, PREMIUM, BAR}

    /**
     * Ticket Id. UNIQUE.
     *
     * @return Ticket Id.
     */
    long getId();

    void setId(long id);

    Event getEvent();

    void setEvent(Event event);

    User getUser();

    void setUser(User user);

    Category getCategory();

    void setCategory(Category category);

    int getPlace();

    void setPlace(int place);

}
