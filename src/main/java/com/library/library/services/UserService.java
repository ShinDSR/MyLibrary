package com.library.library.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.library.models.entities.User;
import com.library.library.models.repos.UserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User save(User user) {
        return userRepo.save(user);
    }

    public User findOne(long id) {
        Optional<User> user = userRepo.findById(id);
        if (!user.isPresent()) {
            return null;
        }
        return userRepo.findById(id).get();
    }

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public void removeOne(long id) {
        userRepo.deleteById(id);
    }
}
