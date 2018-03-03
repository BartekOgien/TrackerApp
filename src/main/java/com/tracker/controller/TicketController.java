package com.tracker.controller;

import com.tracker.facade.TrackerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TicketController {
    @Autowired
    private TrackerFacade trackerFacade;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public String postComment(Model model, HttpServletRequest request, @RequestParam String comment){
        return trackerFacade.saveComment(model, request, comment);
    }

    @RequestMapping(value = "/commentTemplate", method = RequestMethod.GET)
    public String addComment(HttpServletRequest request, @RequestParam int id){
        return trackerFacade.addNewComment(request, id);
    }

    @RequestMapping(value = "/selectTicket", method = RequestMethod.GET)
    public String selectTicket(Model model, HttpServletRequest request, @RequestParam int id){
        return trackerFacade.selectTicket(model, request, id);
    }

    @RequestMapping(value = "/editTicket")
    public String editTicket(Model model, HttpServletRequest request, @RequestParam int userId, String status, String title, String description){
        return trackerFacade.editTicket(model, request, userId, status, title, description);
    }

    @RequestMapping(value = "/deleteTicket")
    public String deleteTicket(Model model, @RequestParam int id){
        return trackerFacade.deleteTicket(model, id);
    }

    @RequestMapping(value = "/ticketList", method = RequestMethod.GET)
    public String getTicketList(Model model){
        return trackerFacade.getListOfAllTickets(model);
    }

    @RequestMapping(value = "/ticketListAddTicket", method = RequestMethod.POST)
    public String getTicketListAddTicket(HttpServletRequest request, Model model, @RequestParam String status, String title, String description){
        return trackerFacade.getListOfTicketsAndNewTicket(request, model, status, title, description);
    }

    @RequestMapping(value = "/newTicket", method = RequestMethod.POST)
    public String addTicket(){
        return trackerFacade.addNewTicket();
    }
}
