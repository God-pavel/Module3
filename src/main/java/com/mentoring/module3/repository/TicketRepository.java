package com.mentoring.module3.repository;

import com.mentoring.module3.model.impl.Event;
import com.mentoring.module3.model.impl.Ticket;
import com.mentoring.module3.model.impl.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {

    @Override
    @Cacheable("tickets")
    Optional<Ticket> findById(Long aLong);

    List<Ticket> findAllByUser(User user, Pageable pageRequest);

    List<Ticket> findAllByEvent(Event event, Pageable pageRequest);

    Optional<Ticket> findByPlaceAndEventId(int place, long eventId);

    boolean existsByEventIdAndPlace(long eventId, int place);
}
