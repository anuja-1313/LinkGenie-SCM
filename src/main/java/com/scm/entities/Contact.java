package com.scm.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.*;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    private String cloudinaryImagePublicId;

    //private List<String> socialLinks = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties("contacts")
    private User user;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<SocialLink> socialLink = new ArrayList<>();

}


