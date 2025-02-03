package com.scm.controller;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helper.Helper;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.ContactService;
import com.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    //add contact page handler
    @RequestMapping("/add")
    public String addContactView(Model model){

        //sending blank contact form object from controller
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm",contactForm);
        contactForm.setFavourite(true);

        return "user/add_contact";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult,
                              Authentication authentication, HttpSession httpSession){

        //validate form
        if(bindingResult.hasErrors()){
            httpSession.setAttribute("message", Message.builder()
                    .content("Please resolve the following errors!")
                    .type(MessageType.red)
                    .build());
            return "user/add_contact";
        }

        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);

        //converting form to contact
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavourite(contactForm.isFavourite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setLinkedinLink(contactForm.getLinkedInLink());
        contact.setUser(user);

        contactService.save(contact);
        System.out.println(contactForm);

        //message to be displayed on view
        httpSession.setAttribute("message", Message.builder()
                .content("Contact added successfully!")
                .type(MessageType.green)
                .build());

        return "redirect:/user/contact/add";
    }

}
