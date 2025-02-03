package com.scm.services;

import com.scm.entities.Contact;

import java.util.List;

public interface ContactService {
    //save contacts
    Contact save(Contact contact);

    //update contact
    Contact update(Contact contact);

    //get contacts
    List<Contact> getAll();

    //get contacts by id
    Contact getById(String id);

    //delete contact
    void delete(String id);

    //search contact
    List<Contact> search(String name,String email, String phoneNumber);

    //get contacts by userid
    List<Contact> getByUserId(String userId);
}
