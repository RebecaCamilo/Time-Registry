package com.project.timeRegistry.repository;

import com.project.timeRegistry.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Integer countByLogin(String login);
}

