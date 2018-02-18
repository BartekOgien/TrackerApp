package com.tracker.contoller;

import com.tracker.dao.TicketDao;
import com.tracker.dao.UserDao;
import com.tracker.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TrackerController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TicketDao ticketDao;

    @RequestMapping("/")
    public String runIndex(){
        return "index";
    }

    @RequestMapping(value = "/loggedIn")
    public String validation(Model model){
        String user = "Kalwas";
        model.addAttribute("username", user);
        return "loggedIn";
    }

    @RequestMapping(value = "/ticketList")
    public String getTicketList(Model model){
        List<Ticket> ticketList = ticketDao.findAll();
        model.addAttribute("listOfTickets", ticketList);
        return "ticketList";
    }

    @RequestMapping("/test")
    public String homeCos(){
        return "cos";
    }
}
