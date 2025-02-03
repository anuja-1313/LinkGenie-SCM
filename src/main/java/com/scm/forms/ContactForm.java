package com.scm.forms;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactForm {

    private String name;

    private String email;

    private String phoneNumber;

    private String address;

    private String description;

    private boolean favourite;

    private String websiteLink;

    private String linkedInLink;

    //for contact image
    private MultipartFile profileImage;


}
