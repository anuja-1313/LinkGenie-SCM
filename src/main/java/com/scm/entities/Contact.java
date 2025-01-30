package com.scm.entities;

import jakarta.persistence.*;

import java.util.*;
import java.util.ArrayList;

@Entity
@Table(name="contacts")
public class Contact {

    @Id
    private String id;

    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;

    @Column(length = 1000)
    private String description;
    private boolean favourite = false;

    private String websiteLink;
    private String linkedinLink;

    //private List<String> socialLinks = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SocialLink> socialLink = new ArrayList<>();

}


