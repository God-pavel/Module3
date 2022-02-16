package com.mentoring.module3.repository;

import com.mentoring.module3.model.impl.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Override
    @Cacheable("users")
    Optional<User> findById(Long aLong);

    User findByEmail(String email);

    List<User> findAllByNameContains(String name, Pageable pageRequest);
}
