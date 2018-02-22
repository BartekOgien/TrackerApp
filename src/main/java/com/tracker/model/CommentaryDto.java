package com.tracker.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class CommentaryDto {
    private int id;
    private String comment;
    private String userName;
    private Date created;
    private TicketDto ticket;

    public CommentaryDto(int id, String comment, String userName, Date created, TicketDto ticket) {
        this.id = id;
        this.comment = comment;
        this.userName = userName;
        this.created = created;
        this.ticket = ticket;
    }
}
