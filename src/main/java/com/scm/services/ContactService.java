package com.scm.services;

import com.scm.entities.Contact;
import com.scm.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    List<Contact> searchByName(String nameKeyword);
    List<Contact> searchByEmail(String emailKeyword);
    List<Contact> searchByPhoneNumber(String phoneNumberKeyword);

    //get contacts by userid
    List<Contact> getByUserId(String userId);

    //list all contacts
    Page<Contact> getByUser(User user, int page, int size, String sortField, String sortDirection);

}
