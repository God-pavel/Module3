package com.mentoring.module3.repository.impl;

import com.mentoring.module3.model.impl.Event;
import com.mentoring.module3.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EventRepositoryImplTest {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private EventRepository repository;

    @BeforeEach
    void setUp() {
        repository.save(Event.builder().id(1).title("title1").date(Date.from(Instant.now())).build());
        repository.save(Event.builder().id(2).title("title2").date(Date.from(Instant.now())).build());
    }

    @Test
    void test1() {
        Optional<Event> event = repository.findById(1L);

        assertEquals(event, getCachedEvents(1L));
    }

    @Test
    void test2() {
        repository.findById(1L);

        assertEquals(Optional.empty(), getCachedEvents(2L));
    }

    private Optional<Event> getCachedEvents(long id) {
        return Optional.ofNullable(cacheManager.getCache("events")).map(c -> c.get(id, Event.class));
    }
}