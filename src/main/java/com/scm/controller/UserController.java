package com.scm.controller;

import com.scm.helper.Helper;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    //user dashboard page
    @RequestMapping(value = "/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    @RequestMapping(value = "/profile")
    public String userProfile(Authentication authentication){

        String username = Helper.getEmailOfLoggedInUser(authentication);

        logger.info("User logged in: " + username);

        System.out.println("User Profile");

        return "user/profile";
    }

    //user add contact

    //view contact

    //delete contact

    //edit contact

    //search contact
}


