package com.scm.controller;

import com.scm.forms.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String signup(Model model){
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "signup";
    }

    //processing registration

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm){
        System.out.println("Processing registration");
        //fetch form data
        //UserForm
        //validate
        //save to database
        // message = registration successful
        //redirect to login page
        return "redirect:/signup";
    }

}
