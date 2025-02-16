package com.scm.controller;

import com.scm.entities.User;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    //verify email
    @GetMapping("/verify-email")
    public String verifyEmail(
            @RequestParam("token") String token,
            HttpSession session
    ){
        User user = userRepo.findByEmailToken(token).orElse(null);

        if(user != null){

            if(user.getEmailToken().equals(token)){
                user.setEmailVerified(true);
                user.setEnabled(true);
                userRepo.save(user);

                //message after successful verification
                session.setAttribute("message", Message.builder()
                        .type(MessageType.green)
                        .content("Email verification successful! Please login to continue.")
                        .build());

                return "success_page";
            }

            return "error_page";
        }

        //message when verification fails
        session.setAttribute("message", Message.builder()
                .type(MessageType.red)
                .content("Email verification failed! Token not associated with user.")
                .build());

        return "error_page";
    }
}
