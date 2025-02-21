package com.library.library.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.models.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
