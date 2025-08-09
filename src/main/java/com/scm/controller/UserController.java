package com.scm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.services.ContactService;
import com.scm.services.UserService;

@Controller 
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;
    

    // User dashboard page
    @RequestMapping(value = "/dashboard")
    // public String requestMethodName() {
        // System.out.println("User dashboard opening");
        // return "/user/dashboard";


        public String showUpcomingBirthdays(Model model, Authentication authentication) {
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        List<Contact> upcomingBirthdays = contactService.getUpcomingBirthdays(user);
        
        System.out.println("-------------------------------------------------------------");
        logger.info("Upcoming Birthdays: {}", upcomingBirthdays);
        model.addAttribute("upcomingBirthdays", upcomingBirthdays); 
        model.addAttribute("title", "Upcoming Birthdays");

        return "user/dashboard"; // Thymeleaf page
    }

    // User profile page
    @RequestMapping(value = "/profile")
    public String userProfile(Model model, Authentication authentication) {

        return "user/profile";
    }

    // User add contacts page

    // User view contacts

    // User edit contact

    // User delete contact

    //  @GetMapping("/user/dashboard")
    
    // }

}
