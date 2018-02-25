package com.tracker.mapper;

import com.tracker.model.domain.Commentary;
import com.tracker.model.domain.Ticket;
import com.tracker.model.domain.User;
import com.tracker.model.dto.CommentaryDto;
import com.tracker.model.dto.TicketDto;
import com.tracker.model.dto.UserDto;
import com.tracker.repository.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MapperTest {
    @InjectMocks
    private Mapper mapper;

    @Mock
    private Mapper mapperMock;

    @Mock
    private UserDao userDao;

    @Test
    public void shouldMapToUserDto() {
        //Given
        User user = new User(1, "Username", "Password");

        //When
        UserDto userDto = mapper.mapToUserDto(user);

        //Then
        assertEquals(1, userDto.getId());
        assertEquals("Username", userDto.getUserName());
    }

    @Test
    public void shouldMapToCommentaryDto() {
        //Given
        User user = new User(1, "Username", "Password");
        UserDto userDto = new UserDto(1, "Username");
        Ticket ticket = new Ticket(user, user, "status", "title", "description");
        Commentary commentary = new Commentary("Commentary", user, ticket);
        Date commentaryCreatedDate = commentary.getCreated();
        when(mapperMock.mapToUserDto(user)).thenReturn(userDto);

        //When
        CommentaryDto commentaryDto = mapper.mapToCommentaryDto(commentary);

        //Then
        assertEquals("Commentary", commentaryDto.getComment());
        assertEquals("Username", commentaryDto.getUser().getUserName());
        assertEquals(commentaryCreatedDate, commentaryDto.getCreated());
    }

    @Test
    public void shouldMapToTicket() {
        //Given
        UserDto userDto = new UserDto(1, "username");
        User user = new User(1, "username", "password");
        TicketDto ticketDto = new TicketDto(userDto, userDto, "status", "title", "description", new ArrayList<>());
        when(userDao.findOne(userDto.getId())).thenReturn(user);

        //When
        Ticket resultTicket = mapper.mapToTicket(ticketDto);

        //Then
        assertEquals("username", resultTicket.getReportedUser().getUserName());
        assertEquals("title", resultTicket.getTitle());
    }

    @Test
    public void shouldMapToTicketDto() {
        //Given
        User user = new User(1, "username", "password");
        UserDto userDto = new UserDto(1, "username");
        Ticket ticket = new Ticket(user, user, "status", "title", "description");
        when(mapperMock.mapToUserDto(user)).thenReturn(userDto);

        //When
        TicketDto resultTicketDto = mapper.mapToTicketDto(ticket);

        //Then
        assertEquals("username", resultTicketDto.getReportedUser().getUserName());
    }
}
