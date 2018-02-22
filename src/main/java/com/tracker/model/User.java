package com.tracker.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
public class User {

    @Id
    @NotNull
    @GeneratedValue
    private int id;
    @Column(name = "USERNAME", unique = true)
    private String userName;
    @NotNull
    private String password;
    @OneToMany(targetEntity = Ticket.class,
            mappedBy = "reportedUser",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Ticket> reportedTicketList = new ArrayList<>();
    @OneToMany(targetEntity = Ticket.class,
            mappedBy = "assignedUser",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Ticket> assignedTicketList = new ArrayList<>();
    @OneToMany(targetEntity = Commentary.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Commentary> commentList = new ArrayList<>();

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public User() {
    }
}
