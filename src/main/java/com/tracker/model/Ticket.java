package com.tracker.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TICKETS")
@Getter
public class Ticket {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID")
    private int idNumber;
    @ManyToOne
    @JoinColumn(name = "REPORTED_USER_ID")
    private User reportedUser;
    @ManyToOne
    @JoinColumn(name = "ASSIGNED_USER_ID")
    private User assignedUser;
    private String status;
    private String title;
    private String description;
    @OneToMany(targetEntity = Commentary.class,
            mappedBy = "ticket",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Commentary> commentaryList = new ArrayList<>();

    public Ticket(User reportedUser, User assignedUser, String status, String title, String description) {
        this.reportedUser = reportedUser;
        this.assignedUser = assignedUser;
        this.status = status;
        this.title = title;
        this.description = description;
    }

    public Ticket(int idNumber, User reportedUser, User assignedUser, String status, String title, String description) {
        this.idNumber = idNumber;
        this.reportedUser = reportedUser;
        this.assignedUser = assignedUser;
        this.status = status;
        this.title = title;
        this.description = description;
    }

    public Ticket() {
    }
}
