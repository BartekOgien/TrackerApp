package com.tracker.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "COMMENTS")
@NoArgsConstructor
@Getter
@Setter
public class Commentary {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private int id;
    private String comment;
    private String userName;
    private Date created;
    @ManyToOne
    @JoinColumn(name = "TICKET_ID")
    private Ticket ticket;

    public Commentary(String comment, String userName, Ticket ticket) {
        this.comment = comment;
        this.userName = userName;
        this.created = new Date();
        this.ticket = ticket;
    }
}