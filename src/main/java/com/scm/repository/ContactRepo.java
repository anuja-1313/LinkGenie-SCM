package com.scm.repository;

import com.scm.entities.Contact;
import com.scm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepo extends JpaRepository<Contact, String> {

    //find the contacts by user
    List<Contact> findByUser(User user);

    @Query("Select c from Contact c where c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId")String userId);

}
