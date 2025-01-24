package com.scm.controller;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;

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

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
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
    public String processRegister(@Valid @ModelAttribute UserForm userForm,  BindingResult bindingResult, HttpSession session){
        System.out.println("Processing registration");
        //fetch form data
        //UserForm
        //validate
        //save to database

//        User user = User.builder()
//                .name(userForm.getName())
//                .email(userForm.getEmail())
//                .about(userForm.getAbout())
//                .password(userForm.getPassword())
//                .phoneNumber(userForm.getPhoneNumber())
//                .profilePic("/images/defaultpic.jpg")
//                .build();
        //validating form data
        if(bindingResult.hasErrors()){
            return "signup";
        }

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("/images/defaultpic.jpg");


        User savedUser = userService.saveUser(user);
        System.out.println("User Saved");

        // message = registration successful
        //using session

        Message message = Message.builder().content("Registration Successful!").type(MessageType.green).build();
        session.setAttribute("message", message);

        //redirect to login page
        return "redirect:/signup";
    }

}
