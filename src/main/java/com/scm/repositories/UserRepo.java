package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.entities.User;

public interface UserRepo extends JpaRepository<User, String> 
{
    //Custom finder methods
    
    // nameing wise datajpa will write data base query we don't need to write
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String pasword);

    Optional<User> findByEmailToken(String token);

}
