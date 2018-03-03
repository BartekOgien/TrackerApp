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
public class UserController {
    @Autowired
    private TrackerFacade trackerFacade;

    @RequestMapping("/")
    public String runIndex(){
        return trackerFacade.runHomePage();
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.GET)
    public String showRegisterTemplate(){
        return trackerFacade.showRegisterTemplate();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String registerUser(HttpServletRequest request, @RequestParam String newUsername, String newPassword){
        return trackerFacade.addNewUser(request, newUsername, newPassword);
    }

    @RequestMapping(value = "/validation", method = RequestMethod.POST)
    public String validateUser(HttpServletRequest request, Model model, @RequestParam String username, String userPassword){
        return trackerFacade.validateUsernameAndPassword(request, model, username, userPassword);
    }
}
