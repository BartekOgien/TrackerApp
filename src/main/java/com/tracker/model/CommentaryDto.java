package com.tracker.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class CommentaryDto {
    private int id;
    private String comment;
    private User user;
    private Date created;
    private TicketDto ticket;

    public CommentaryDto(int id, String comment, User user, Date created, TicketDto ticket) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.created = created;
        this.ticket = ticket;
    }
}
