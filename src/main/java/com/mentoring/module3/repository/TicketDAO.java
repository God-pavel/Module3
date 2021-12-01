package com.mentoring.module3.repository;

import com.mentoring.module3.model.impl.Event;
import com.mentoring.module3.model.impl.Ticket;
import com.mentoring.module3.model.impl.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketDAO extends PagingAndSortingRepository<Ticket, Long> {
    List<Ticket> findAllByUser(User user, Pageable pageRequest);

    List<Ticket> findAllByEvent(Event event, Pageable pageRequest);

    boolean existsByEventIdAndPlace(long eventId, int place);
}
