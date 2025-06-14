package com.scm.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import com.scm.entities.User;
import com.scm.helpers.Helper;  
import com.scm.services.UserService;

@Controller 
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    

    // User dashboard page
    @RequestMapping(value = "/dashboard")
    public String requestMethodName() {
        System.out.println("User dashboard opening");
        return "/user/dashboard";
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

}
