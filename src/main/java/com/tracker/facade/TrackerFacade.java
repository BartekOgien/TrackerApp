package com.tracker.facade;

import com.tracker.mapper.Mapper;
import com.tracker.model.domain.Commentary;
import com.tracker.model.domain.Ticket;
import com.tracker.model.domain.User;
import com.tracker.model.dto.TicketDto;
import com.tracker.model.dto.UserDto;
import com.tracker.repository.TicketDao;
import com.tracker.repository.UserDao;
import com.tracker.repository.UserSelector;
import com.tracker.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackerFacade {
    private final static String SESSION_USER = "user";
    private final static String SESSION_TICKET_ID = "id";
    private final static String MODEL_USER_LIST = "userList";
    private final static String MODEL_TICKET_LIST = "listOfTickets";
    private final static String MODEL_TICKETDTO = "ticketDto";

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserSelector userSelector;

    @Autowired
    private Mapper mapper;

    public String getListOfTicketsAndNewTicket(HttpServletRequest request, Model model, String status, String title, String description) {
        UserDto reportedUser = (UserDto)request.getSession().getAttribute(SESSION_USER);
        User assignedUser = userSelector.assignUser();
        Ticket ticket = mapper.mapToTicket(new TicketDto(reportedUser, mapper.mapToUserDto(assignedUser), status, title, description, new ArrayList<>()));
        ticketDao.save(ticket);
        return getListOfAllTickets(model);
    }

    public String runHomePage() {
        return "index";
    }

    public String addNewComment(HttpServletRequest request, int id){
        request.getSession().setAttribute(SESSION_TICKET_ID, id);
        return "newComment";
    }

    public String saveComment(Model model, HttpServletRequest request, String comment){
        UserDto user = (UserDto)request.getSession().getAttribute(SESSION_USER);
        Ticket ticket = ticketDao.findOne((int)request.getSession().getAttribute(SESSION_TICKET_ID));
        ticket.getCommentaryList().add(new Commentary(comment, mapper.mapToUser(user), ticket));
        ticketDao.save(ticket);
        return getListOfAllTickets(model);
    }

    public String selectTicket(Model model, HttpServletRequest request, int id){
        TicketDto ticketDto = mapper.mapToTicketDto(ticketDao.findOne(id));
        model.addAttribute(MODEL_TICKETDTO, ticketDto);
        List<UserDto> userList = userDao.findAll().stream()
                                .map(u ->mapper.mapToUserDto(u))
                                .collect(Collectors.toList());
        model.addAttribute(MODEL_USER_LIST, userList);
        request.getSession().setAttribute(SESSION_TICKET_ID, id);
        return "editTicket";
    }

    public String editTicket(Model model, HttpServletRequest request, int userId, String status, String title, String description){
        Ticket ticket = ticketDao.findOne((int)request.getSession().getAttribute(SESSION_TICKET_ID));
        User assignedUser = userDao.findOne(userId);
        ticket.setAssignedUser(assignedUser);
        ticket.setStatus(status);
        ticket.setTitle(title);
        ticket.setDescription(description);
        ticketDao.save(ticket);
        return getListOfAllTickets(model);
    }

    public String deleteTicket(Model model, int ticketId){
        ticketDao.delete(ticketId);
        return getListOfAllTickets(model);
    }

    public String validateUsernameAndPassword(HttpServletRequest request, Model model, String username, String userPassword){
        if(userValidator.validateUser(request, username, userPassword)) {
            return getListOfAllTickets(model);
        }
        return "index";
    }

    public String addNewUser(HttpServletRequest request, String username, String userPassword){
        if(userValidator.checkLoginIsUnique(request, username)) {
            userDao.save(new User(username, userPassword));
            return "index";
        }
        else {
            return "newUser";
        }
    }

    public String addNewTicket() {
        return "newTicket";
    }

    public String showRegisterTemplate() {
        return "newUser";
    }

    public String getListOfAllTickets(Model model) {
        List<TicketDto> ticketDtoList = mapper.mapToTicketDtoList(ticketDao.findAll());
        model.addAttribute(MODEL_TICKET_LIST, ticketDtoList);
        return "ticketList";
    }
}
