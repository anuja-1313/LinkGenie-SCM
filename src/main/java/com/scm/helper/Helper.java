package com.scm.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;


@Component
public class Helper {

    @Value("${server.baseUrl}")
    private String baseUrl;

    public static String getEmailOfLoggedInUser(Authentication authentication){


        //getting email if logged in via email and password
        if(authentication instanceof OAuth2AuthenticationToken) {

            var oauthToken = (OAuth2AuthenticationToken)authentication;
            var clientId = oauthToken.getAuthorizedClientRegistrationId();

            var oauth2User = (OAuth2User)authentication.getPrincipal();
            String username = "";

            // sign in with Google
            if(clientId.equalsIgnoreCase("google")){
                System.out.println("Getting email from Google");
                username = oauth2User.getAttribute("email");
            }
            //sign in with GitHub
            else if (clientId.equalsIgnoreCase("github")){
                System.out.println("Getting email from Github");

                username = oauth2User.getAttribute("email") != null
                        ? oauth2User.getAttribute("email").toString()
                        : oauth2User.getAttribute("login").toString() + "@github.com";

            }

            return username;

        } else {
            System.out.println("Getting data from local database");
            return authentication.getName();
        }
    }

    public String getLinkForEmailVerification(String emailToken){
        String link = this.baseUrl + "/auth/verify-email?token=" + emailToken;
        return link;
    }
}
