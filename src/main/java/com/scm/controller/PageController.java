package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    //Home Page
    @RequestMapping("/home")
    public String home(){

        return "home";
    }

    //About page
    @RequestMapping("/about")
    public String aboutPage(){
        return "about";
    }

    //Services Page
    @RequestMapping("/services")
    public String services(){

        return "services";
    }

    //contact
    @GetMapping("/contact")
    public String contact(){
        return new String ("contact");
    }

    @GetMapping("/login")
    public String login(){
        return new String ("login");
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

}
