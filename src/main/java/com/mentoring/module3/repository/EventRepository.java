package com.mentoring.module3.repository;

import com.mentoring.module3.model.impl.Event;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    @Override
    @Cacheable("events")
    Optional<Event> findById(Long aLong);

    List<Event> findAllByTitleContains(String string, Pageable pageRequest);

    List<Event> findAllByDate(Date date, Pageable pageRequest);
}
