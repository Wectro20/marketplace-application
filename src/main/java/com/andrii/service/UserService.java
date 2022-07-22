package com.andrii.service;

import com.andrii.models.User;
import com.andrii.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Service
@ApplicationScope
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public User addUser(final User user) {
        return userRepository.save(user);
    }

    public User updateUser(final User user) {
        return userRepository.save(user);
    }

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public User getUserId(final Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
    }
}
