package com.tracker.facade;

import com.tracker.mapper.Mapper;
import com.tracker.model.*;
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

@Service
public class TrackerFacade {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserSelector userSelector;

    @Autowired
    Mapper mapper;

    public String getListOfTickets(Model model) {
        return getListOfAllTickets(model);
    }

    public String getListOfTicketsAndNewTicket(HttpServletRequest request, Model model, String status, String title, String description) {
        UserDto reportedUser = (UserDto)request.getSession().getAttribute("user");
        User assignedUser = userSelector.assignUser();
        Ticket ticket = mapper.mapToTicket(new TicketDto(reportedUser, mapper.mapToUserDto(assignedUser), status, title, description, new ArrayList<>()));
        ticketDao.save(ticket);
        return getListOfAllTickets(model);
    }

    public String runHomePage() {
        return "index";
    }

    public String addNewComment(HttpServletRequest request, int id){
        request.getSession().setAttribute("id", id);
        return "newComment";
    }

    public String saveComment(Model model, HttpServletRequest request, String comment){
        UserDto user = (UserDto)request.getSession().getAttribute("user");
        Ticket ticket = ticketDao.findOne((int)request.getSession().getAttribute("id"));
        ticket.getCommentaryList().add(new Commentary(comment, mapper.mapToUser(user), ticket));
        ticketDao.save(ticket);
        return getListOfAllTickets(model);
    }

    public String editTicket(){
        return "singleTicket";
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
        model.addAttribute("listOfTickets", ticketDtoList);
        return "ticketList";
    }
}
