package com.scm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.scm.entities.Contact;
import com.scm.entities.User;

public interface UserRepo extends JpaRepository<User, String> 
{
    //Custom finder methods
    
    // nameing wise datajpa will write data base query we don't need to write
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String pasword);

    Optional<User> findByEmailToken(String token);

    
    // // userRepo.java
    //         @Query("SELECT c FROM Contact c WHERE c.user.id = :userId AND c.birthday IS NOT NULL AND FUNCTION('MONTH', c.birthday) = :month AND FUNCTION('DAY', c.birthday) BETWEEN :startDay AND :endDay")
    // List<Contact> findUpcomingBirthdays(
    //         @Param("userId") String userId,
    //         @Param("month") int month,
    //         @Param("startDay") int startDay,
    //         @Param("endDay") int endDay);


}
