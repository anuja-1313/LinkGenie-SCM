package com.scm.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Entity(name="user")
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class User implements UserDetails {

    @Id
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String name;


    @Column(unique = true, nullable = false)
    private String email;


    @Getter(AccessLevel.NONE)
    private String password;


    @Column(length = 1000)
    private String about; //user status


    @Column(length = 1000)
    private String profilePic;


    private String phoneNumber;

    //information

    private boolean emailVerified = false;

    private boolean phoneVerified = false;


    @Getter(value = AccessLevel.NONE)
    private boolean enabled = false;


    //user signup method
    //SELF, GOOGLE, Github

    @Enumerated(value = EnumType.STRING)//to show provider as a string in db
    private Providers provider = Providers.SELF;

    private String providerId;

    //ONE TO MANY mapping

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();


    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

    private String emailToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles;
        roles = roleList.stream().map(role->new SimpleGrantedAuthority(role)).collect((Collectors.toList()));
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}



