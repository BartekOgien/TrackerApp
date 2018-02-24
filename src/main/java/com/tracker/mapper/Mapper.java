package com.tracker.mapper;

import com.tracker.model.domain.Commentary;
import com.tracker.model.domain.Ticket;
import com.tracker.model.domain.User;
import com.tracker.model.dto.CommentaryDto;
import com.tracker.model.dto.TicketDto;
import com.tracker.model.dto.UserDto;
import com.tracker.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mapper {
    @Autowired
    private Mapper mapper;

    @Autowired
    private UserDao userDao;

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getUserName());
    }

    public User mapToUser(UserDto userDto) {
        return userDao.findOne(userDto.getId());
    }

    public TicketDto mapToTicketDto(Ticket ticket) {
        List<CommentaryDto> commentaryDtoList = ticket.getCommentaryList().stream()
                    .map(c -> mapToCommentaryDto(c))
                    .collect(Collectors.toList());
        return new TicketDto(ticket.getIdNumber(), mapToUserDto(ticket.getReportedUser()), mapToUserDto(ticket.getAssignedUser()),
                ticket.getStatus(), ticket.getTitle(), ticket.getDescription(), commentaryDtoList);
    }

    public Ticket mapToTicket(TicketDto ticketDto) {
        return new Ticket(mapToUser(ticketDto.getReportedUser()), mapToUser(ticketDto.getAssignedUser()),
                ticketDto.getStatus(), ticketDto.getTitle(), ticketDto.getDescription());
    }

    public CommentaryDto mapToCommentaryDto(Commentary commentary) {
        return new CommentaryDto(commentary.getComment(), mapper.mapToUserDto(commentary.getUser()), commentary.getCreated());
    }

    public List<TicketDto> mapToTicketDtoList(List<Ticket> ticketList) {
        return ticketList.stream().map(t -> mapToTicketDto(t))
                .collect(Collectors.toList());
    }
}
