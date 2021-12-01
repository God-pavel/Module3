package com.mentoring.module3.repository;

import com.mentoring.module3.model.impl.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends PagingAndSortingRepository<User, Long> {
    User findByEmail(String email);

    List<User> findAllByNameContains(String name, Pageable pageRequest);
}
