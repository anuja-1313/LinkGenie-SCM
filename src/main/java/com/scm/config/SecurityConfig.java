package com.scm.config;

import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;


@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //user create and login page, using java code using in-memory service
    @Bean
    public UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder){

        UserDetails admin = User.withUsername("admin")
                .roles("ADMIN","USER")
                .password("admin").build();

        UserDetails user1 = User.withUsername("user123")
                .password("password").build();

        return new InMemoryUserDetailsManager(admin,user1);
    }

}
