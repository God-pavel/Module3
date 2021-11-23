package com.mentoring.module2.model.impl;

import com.mentoring.module2.model.Event;
import com.mentoring.module2.model.Ticket;
import com.mentoring.module2.model.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "tickets")
public class TicketImpl implements Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id")
    private User user;
    //TODO check FetchType
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "events_id")
    private Event event;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "place", nullable = false)
    private int place;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}
