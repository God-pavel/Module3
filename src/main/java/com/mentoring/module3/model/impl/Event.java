package com.mentoring.module3.model.impl;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "date", nullable = false)
    private Date date;
}
