package com.mentoring.module3.repository;

import com.mentoring.module3.model.impl.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventDAO extends PagingAndSortingRepository<Event, Long> {

    List<Event> findAllByTitleContains(String string, Pageable pageRequest);

    List<Event> findAllByDate(Date date, Pageable pageRequest);
}
