package com.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private int id;
    private String userName;
    private String password;
}
