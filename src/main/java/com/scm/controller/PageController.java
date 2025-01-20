package com.scm.controller;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.services.UserService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {


    //injecting service
    @Autowired
    private UserService userService;

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

        User user = User.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .about(userForm.getAbout())
                .password(userForm.getPassword())
                .phoneNumber(userForm.getPhoneNumber())
                .profilePic("/images/defaultpic.jpg")
                .build();
        User savedUser = userService.saveUser(user);
        System.out.println("User Saved");

        // message = registration successful
        //redirect to login page
        return "redirect:/signup";
    }

}
