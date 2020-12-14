package com.epam.user.repository;


import com.epam.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from Users u where u.userName = ?1")
    Optional<User> findByUserName(String userName);
}
