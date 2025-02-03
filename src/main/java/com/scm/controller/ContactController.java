package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

    //add contact page handler
    @RequestMapping("/add")
    public String addContactView(){

        return "user/add_contact";
    }

}
