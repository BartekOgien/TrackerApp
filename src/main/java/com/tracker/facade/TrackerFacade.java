package com.tracker.facade;

import com.tracker.mapper.Mapper;
import com.tracker.model.Commentary;
import com.tracker.model.Ticket;
import com.tracker.model.TicketDto;
import com.tracker.model.User;
import com.tracker.repository.TicketDao;
import com.tracker.repository.UserDao;
import com.tracker.repository.UserSelector;
import com.tracker.repository.VariableRepository;
import com.tracker.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
        List<TicketDto> ticketList = mapper.mapToTicketDtoList(ticketDao.findAll());
        model.addAttribute("listOfTickets", ticketList);
        return "ticketList";
    }

    public String getListOfTicketsAndNewTicket(Model model, String status, String title, String description) {
        User reportedUser = VariableRepository.getCurrentUser();
        User assignedUser = userSelector.assignUser();
        Ticket ticket = mapper.mapToTicket(new TicketDto(reportedUser, assignedUser, status, title, description));
        ticketDao.save(ticket);
        List<TicketDto> ticketDtoList = mapper.mapToTicketDtoList(ticketDao.findAll());
        model.addAttribute("listOfTickets", ticketDtoList);
        return "ticketList";
    }

    public String runHomePage() {
        return "index";
    }

    public String addNewComment(int id){
        Ticket ticket = ticketDao.findOne(id);
        ticket.getCommentaryList().add(new Commentary("dziala", VariableRepository.getCurrentUser(), ticket));
        ticketDao.save(ticket);
        return "newComment";
    }

    public String editTicket(){
        return "singleTicket";
    }

    public String validateUsernameAndPassword(Model model, String username, String userPassword){
        boolean validation = userValidator.validateUser(username, userPassword);
        model.addAttribute("validation", validation);
        return "validation";
    }

    public String addNewUser(String username, String userPassword){
        if(userValidator.checkLoginIsUnique(username)) {
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
}
