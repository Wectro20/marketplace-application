package com.andrii.controllers;

import com.andrii.dto.UserDto;
import com.andrii.exceptions.MarketplaceNotFoundException;
import com.andrii.models.User;
import com.andrii.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "id") Integer id) {
        if (userService.getUserId(id) == null) {
            LOGGER.info("Can't update user with non-existing id" + id);
            throw new MarketplaceNotFoundException("User with id: " + id + " not found");
        }
        LOGGER.info("Successfully gave an object:" + id);
        User user = userService.getUserId(id);
        return new ResponseEntity<UserDto>(new UserDto(user), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUser() {
        LOGGER.info("Successfully gave an objects");
        List<User> users = userService.getUser();
        List<UserDto> usersDto = new ArrayList<>();
        for (User user:users) {
            UserDto userDto = new UserDto(user);
            usersDto.add(userDto);
        }
        return new ResponseEntity<List<UserDto>>(usersDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody User user) {
        userService.addUser(user);
        LOGGER.info("Success added user");
        return new ResponseEntity<UserDto>(new UserDto(user), HttpStatus.OK);
    }

    @PutMapping(path="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@PathVariable("id")final int id, @Valid @RequestBody final User user) {
        if (userService.getUserId(id) == null) {
            LOGGER.info("Can't update user without id - null value was passed instead of it");
            throw new MarketplaceNotFoundException("User with id: " + id + " not found");
        }
        LOGGER.info("Updated an user with id: " + id);
        user.setId(id);
        userService.updateUser(user);
        return new ResponseEntity<UserDto>(new UserDto(user), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
        if (userService.getUserId(id) == null) {
            LOGGER.info("Can't delete user ");
            throw new MarketplaceNotFoundException("User with id: " + id + " not found");
        }
        LOGGER.info("Successfully deleted user with id: " +id);
        userService.getUserId(id);
        return ResponseEntity.noContent().build();
    }
}
