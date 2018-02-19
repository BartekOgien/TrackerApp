package com.tracker.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TICKETS")
@NoArgsConstructor
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private int idNumber;
    private String reportedUser;
    private String assignedUser;
    private String status;
    private String title;
    private String description;
    @OneToMany(targetEntity = Commentary.class,
            mappedBy = "ticket",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Commentary> commentaryList = new ArrayList<>();


    public Ticket(String reportedUser, String assignedUser, String status, String title, String description) {
        this.reportedUser = reportedUser;
        this.assignedUser = assignedUser;
        this.status = status;
        this.title = title;
        this.description = description;
    }
}
