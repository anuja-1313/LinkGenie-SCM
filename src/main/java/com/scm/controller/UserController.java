package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    //user dashboard page
    @RequestMapping(value = "/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    @RequestMapping(value = "/profile")
    public String userProfile(){
        return "user/profile";
    }

    //user add contact

    //view contact

    //delete contact

    //edit contact

    //search contact
}


