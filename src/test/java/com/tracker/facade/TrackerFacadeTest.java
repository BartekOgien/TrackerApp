package com.tracker.facade;

import com.tracker.mapper.Mapper;
import com.tracker.model.domain.Ticket;
import com.tracker.model.domain.User;
import com.tracker.model.dto.TicketDto;
import com.tracker.model.dto.UserDto;
import com.tracker.repository.TicketDao;
import com.tracker.repository.UserDao;
import com.tracker.repository.UserSelector;
import com.tracker.validator.UserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrackerFacadeTest {

    @InjectMocks
    private TrackerFacade trackerFacade;

    @Mock
    private Mapper mapper;

    @Mock
    private Model model;

    @Mock
    private UserValidator userValidator;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private UserDao userDao;

    @Mock
    private TicketDao ticketDao;

    @Mock
    private UserSelector userSelector;

    @Test
    public void shouldGetTaskList() {
        //Given
        List<Ticket> ticketList = new ArrayList<>();
        List<TicketDto> ticketDtoList = new ArrayList<>();
        User user = new User("username", "password");
        UserDto userDto = new UserDto(1, "username");
        ticketList.add(new Ticket(user, user, "status", "title", "description"));
        ticketDtoList.add(new TicketDto(userDto, userDto, "status", "title",
                "description", new ArrayList<>()));
        when(ticketDao.findAll()).thenReturn(ticketList);
        when(mapper.mapToTicketDtoList(ticketList)).thenReturn(ticketDtoList);

        //When
        String resultJspName = trackerFacade.getListOfAllTickets(model);

        //Then
        verify(model, times(1)).addAttribute(anyString(), anyObject());
        assertEquals("ticketList", resultJspName);
    }

    @Test
    public void shouldAddNewUser() {
        //Given
        User user = new User("username", "password");
        when(userValidator.checkLoginIsUnique(request, user.getUserName())).thenReturn(false);

        //When
        String resultJsp = trackerFacade.addNewUser(request, user.getUserName(), user.getPassword());

        //Then
        assertEquals("newUser", resultJsp);
    }

    @Test
    public void shouldDeleteTicket() {
        //Given
        int id = 1;
        doNothing().when(ticketDao).delete(id);

        //When
        String resultJsp = trackerFacade.deleteTicket(model, id);

        //Then
        assertEquals("ticketList", resultJsp);
    }

    @Test
    public void shouldGetListAndAddNewTicket() {
        //Given
        User user = new User("username", "password");
        UserDto userDto = new UserDto(1, "username");
        Ticket ticket = new Ticket(user, user, "status", "title", "description");
        TicketDto ticketDto = new TicketDto(userDto, userDto, "status", "title",
                "description", new ArrayList<>());
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn(userDto);
        when(userSelector.assignUser()).thenReturn(user);
        when(mapper.mapToTicket(ticketDto)).thenReturn(ticket);

        //When
        String resultJsp = trackerFacade.getListOfTicketsAndNewTicket(request, model,
                "status", "title", "description");

        //Then
        assertEquals("ticketList", resultJsp);
    }
}