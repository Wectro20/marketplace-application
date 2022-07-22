package com.andrii.dto;

import com.andrii.models.User;

public class UserDto {
    private User user;

    public UserDto(User user) {
        this.user = user;
    }

    public Integer getId() {
        return user.getId();
    }

    public String getUserFirstName() {
        return user.getFirstName();
    }

    public String getUserSecondName() {
        return user.getSecondName();
    }

    public  Float getUserAmountOfMoney() {
        return user.getAmountOfMoney();
    }
}
