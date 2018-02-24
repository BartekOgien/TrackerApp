package com.tracker.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class CommentaryDto {
    private String comment;
    private UserDto user;
    private Date created;
}
