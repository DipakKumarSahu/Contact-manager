package com.scm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scm.entities.Contact;
import com.scm.entities.User;

public interface ContactService 
{
    //save contacts
     Contact save(Contact contact);

     //update contacts
     Contact update(Contact contact);

     //get all contacts
     List<Contact>getAll();

     //get contact by id
     Contact getById(String id);

     //delete contact by id
     void delete(String id);

     //search contact by name
     Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);

     // search contact by email
     Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);

     //search by Phone number
     Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy, String order, User user);

     //get contacts by user id
     List<Contact> getByUserId(String userId);

     //get Contacts by user
     Page<Contact> getByUser(User user, int page, int size, String sortField, String sortDirection);


     // ContactService.java
     List<Contact> getUpcomingBirthdays(User user);

     
}
