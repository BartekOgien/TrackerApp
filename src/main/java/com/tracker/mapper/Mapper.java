package com.tracker.mapper;

import com.tracker.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mapper {

    @Autowired
    private Mapper mapper;

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getUserName(), user.getPassword());
    }

    public User mapToUser(UserDto userDto) {
        return new User(userDto.getUserName(), userDto.getPassword());
    }

    public TicketDto mapToTicketDto(Ticket ticket) {
        return new TicketDto(ticket.getIdNumber(), ticket.getReportedUser(), ticket.getAssignedUser(),
                ticket.getStatus(), ticket.getTitle(), ticket.getDescription(), ticket.getCommentaryList());
    }

    public Ticket mapToTicket(TicketDto ticketDto) {
        return new Ticket(ticketDto.getReportedUser(), ticketDto.getAssignedUser(),
                ticketDto.getStatus(), ticketDto.getTitle(), ticketDto.getDescription());
    }

    public Commentary mapToCommentary(CommentaryDto commentaryDto) {
        return new Commentary(commentaryDto.getId(), commentaryDto.getComment(), commentaryDto.getUser(), mapper.mapToTicket(commentaryDto.getTicket()));
    }

    public CommentaryDto mapToCommentaryDto(Commentary commentary) {
        return new CommentaryDto(commentary.getId(), commentary.getComment(), commentary.getUser(), commentary.getCreated(),
                mapper.mapToTicketDto(commentary.getTicket()));
    }

    public List<TicketDto> mapToTicketDtoList(List<Ticket> ticketList) {
        return ticketList.stream().map(t -> mapToTicketDto(t))
                .collect(Collectors.toList());
    }
}
