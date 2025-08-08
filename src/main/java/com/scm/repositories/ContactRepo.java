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

    // @Query("SELECT c FROM Contact c WHERE FUNCTION('DAY', c.birthday) >= FUNCTION('DAY', CURRENT_DATE) " +
    //         "AND FUNCTION('MONTH', c.birthday) = FUNCTION('MONTH', CURRENT_DATE) " +
    //         "AND FUNCTION('DAY', c.birthday) <= FUNCTION('DAY', CURRENT_DATE) + 7 " +
    //         "AND c.user.id = :userId")
    // List<Contact> findUpcomingBirthdays(@Param("userId") Long userId);



    // ContactRepo.java
            @Query("SELECT c FROM Contact c WHERE c.user.id = :userId AND c.birthday IS NOT NULL AND FUNCTION('MONTH', c.birthday) = :month AND FUNCTION('DAY', c.birthday) BETWEEN :startDay AND :endDay")
    List<Contact> findUpcomingBirthdays(
            @Param("userId") String userId,
            @Param("month") int month,
            @Param("startDay") int startDay,
            @Param("endDay") int endDay);

}
