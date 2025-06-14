package com.scm.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.entities.Contact;
import com.scm.entities.User;

@Repository
public interface ContactRepo extends JpaRepository <Contact,String> // isme String data type contact ke id se decide hua 
 {

    // find the contact by user
    // It known as cutom finder method
    Page<Contact>findByUser(User user, Pageable pageable);


    
    // contact mein userid ka variable nhi hai wo user table mein hai to yahan humne user se cutom query ke through data nikala 
    //It known as cutom querty method
    @Query("select c from Contact c where c.user.UserId = :userId")
    List<Contact>findByUserId( @Param("userId") String userId);

    Page<Contact> findByUserAndNameContaining(User user, String namekeyword, Pageable pageable);

    Page<Contact> findByUserAndEmailContaining(User user, String emailkeyword, Pageable pageable);

    Page<Contact> findByUserAndPhoneNumberContaining(User user, String phonekeyword, Pageable pageable);

}
