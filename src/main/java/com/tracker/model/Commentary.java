package com.tracker.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "COMMENTS")
@NoArgsConstructor
@Getter
@Setter
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Commentary(int id, String comment, String userName, Ticket ticket) {
        this.id = id;
        this.comment = comment;
        this.userName = userName;
        this.created = new Date();
        this.ticket = ticket;
    }

    public String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm");
        return dateFormat.format(date);
    }
}