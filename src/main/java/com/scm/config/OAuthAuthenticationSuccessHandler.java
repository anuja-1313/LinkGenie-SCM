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
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepo userRepo;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        logger.info("OAuthAuthenticationSuccessHandler");

        //Identify the provider/login
        var oAuth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;
        String authorizedClientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

        logger.info(authorizedClientRegistrationId);

        //checking which attributes are available for each type of login
        var oAuth2User = (DefaultOAuth2User)authentication.getPrincipal();
        oAuth2User.getAttributes().forEach((key, value)->{
            logger.info(key + " : " + value);
        });

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setPassword("dummy");

        if(authorizedClientRegistrationId.equalsIgnoreCase("google")){

            //Google
            user.setEmail(oAuth2User.getAttribute("email").toString());
            user.setProfilePic(oAuth2User.getAttribute("picture"));
            user.setName(oAuth2User.getAttribute("name").toString());
            user.setProviderId(oAuth2User.getName());
            user.setProvider(Providers.GOOGLE);
            user.setAbout("Account created using Google");

        } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")){

            //Github
            String email = oAuth2User.getAttribute("email") != null ? oAuth2User.getAttribute("email").toString()
                    : oAuth2User.getAttribute("login").toString() + "@github.com";

            String picture = oAuth2User.getAttribute("avatar_url").toString();
            String name = oAuth2User.getAttribute("name").toString();
            String providerId = oAuth2User.getName();

            user.setEmail(email);
            user.setProfilePic(picture);
            user.setName(name);
            user.setProviderId(providerId);
            user.setProvider(Providers.GITHUB);
            user.setAbout("Account created using GitHub");

        } else {
            logger.info("Unknown provider");
        }


        User user2 = userRepo.findByEmail(user.getEmail()).orElse(null);
        if(user2 == null){
            userRepo.save(user);
            logger.info("User saved: " + user.getEmail());
        }


        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }


}






