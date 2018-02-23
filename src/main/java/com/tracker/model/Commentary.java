package com.tracker.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "COMMENTS")
@Getter
@Setter
public class Commentary {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private int id;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    private Date created;
    @ManyToOne
    @JoinColumn(name = "TICKET_ID")
    private Ticket ticket;

    public Commentary(String comment, User user, Ticket ticket) {
        this.comment = comment;
        this.user = user;
        this.created = new Date();
        this.ticket = ticket;
    }

    public Commentary(int id, String comment, User user, Ticket ticket) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.created = new Date();
        this.ticket = ticket;
    }

    public Commentary() {
    }
}