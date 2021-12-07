package com.mentoring.module3.repository;

import com.mentoring.module3.model.impl.Event;
import com.mentoring.module3.model.impl.Ticket;
import com.mentoring.module3.model.impl.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {

    @Override
    @Cacheable("tickets")
    Optional<Ticket> findById(Long aLong);

//    @Query(value = "SELECT t " +
//            "from Ticket t " +
//            "join fetch Event " +
//            "where t.id = :id")
//    Optional<Ticket> getById(@Param("id") long id);

    List<Ticket> findAllByUser(User user, Pageable pageRequest);

    List<Ticket> findAllByEvent(Event event, Pageable pageRequest);

    boolean existsByEventIdAndPlace(long eventId, int place);
}
