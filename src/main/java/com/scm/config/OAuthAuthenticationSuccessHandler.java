package com.scm.config;

import com.scm.entities.Providers;
import com.scm.entities.User;
import com.scm.helper.AppConstants;
import com.scm.repository.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepo userRepo;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        logger.info("OAuthAuthenticationSuccessHandler");

        DefaultOAuth2User user =(DefaultOAuth2User) authentication.getPrincipal();

//        logger.info(user.getName());
//
//        user.getAttributes().forEach((key,value) -> {
//           logger.info("{} => {}", key, value);
//        });
//
//        logger.info(user.getAuthorities().toString());

        //save data in database
        String email =user.getAttribute("email").toString();
        String name =user.getAttribute("name").toString();
        String picture =user.getAttribute("picture").toString();

        //create user and save in database
        User user1 = new User();
        user1.setEmail(email);
        user1.setName(name);
        user1.setProfilePic(picture);
        user1.setPassword("password");
        user1.setProvider(Providers.GOOGLE);
        user1.setEnabled(true);
        user1.setEmailVerified(true);
        user1.setProviderId(user.getName());
        user1.setRoleList(List.of(AppConstants.ROLE_USER));
        user1.setAbout("Account created using Google.");

        User user2 = userRepo.findByEmail(email).orElse(null);
        if(user2 == null){
            userRepo.save(user1);
            logger.info("User saved: " + email);
        }


        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }


}






