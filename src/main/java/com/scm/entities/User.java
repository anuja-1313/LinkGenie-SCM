package com.scm.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name="user")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(length = 1000)
    private String about; //user status

    @Column(length = 1000)
    private String profilePic;

    private String phoneNumber;

    //information
    private boolean emailVerified = false;
    private boolean phoneVerified = false;
    private boolean enabled = false;

    //user signup method
    //SELF, GOOGLE, Github
    @Enumerated
    private Providers provider = Providers.SELF;
    private String providerId;

    //ONE TO MANY mapping
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();


}
