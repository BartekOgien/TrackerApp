package com.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TicketDto {
    private int idNumber;
    private User reportedUser;
    private User assignedUser;
    private String status;
    private String title;
    private String description;
    private List<Commentary> commentaryList = new ArrayList<>();

    public TicketDto(int idNumber, User reportedUser, User assignedUser, String status, String title, String description, List<Commentary> commentaryList) {
        this.idNumber = idNumber;
        this.reportedUser = reportedUser;
        this.assignedUser = assignedUser;
        this.status = status;
        this.title = title;
        this.description = description;
        this.commentaryList = commentaryList;
    }

    public TicketDto(User reportedUser, User assignedUser, String status, String title, String description) {
        this.reportedUser = reportedUser;
        this.assignedUser = assignedUser;
        this.status = status;
        this.title = title;
        this.description = description;
    }
}
