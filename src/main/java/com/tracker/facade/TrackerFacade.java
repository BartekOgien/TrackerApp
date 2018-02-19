package com.tracker.facade;

import com.tracker.model.Ticket;
import com.tracker.repository.TicketDao;
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
    private UserValidator userValidator;

    @Autowired
    private UserSelector userSelector;

    public String getListOfTickets(Model model) {
        List<Ticket> ticketList = ticketDao.findAll();
        model.addAttribute("listOfTickets", ticketList);
        return "ticketList";
    }

    public String getListOfTicketsAndNewTicket(Model model, String status, String title, String description) {
        Ticket ticket = new Ticket(VariableRepository.getCurrentUsername(), userSelector.assignUser(), status, title, description);
        ticketDao.save(ticket);
        List<Ticket> ticketList = ticketDao.findAll();
        model.addAttribute("listOfTickets", ticketList);
        return "ticketList";
    }

    public String runHomePage() {
        VariableRepository.setCurrentUsername("");
        return "index";
    }

    public String addNewComment(){
        return "newComment";
    }

    public String editTicket(){
        return "singleTicket";
    }

    public String validateUsernameAndPassword(Model model, String username, String userPassword){
        VariableRepository.setCurrentUsername(username);
        boolean validation = userValidator.validateUser(username, userPassword);
        model.addAttribute("validation", validation);
        return "validation";
    }

    public String addNewTicket() {
        return "newTicket";
    }
}
