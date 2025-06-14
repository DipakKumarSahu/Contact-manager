package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.repositories.UserRepo;
import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.helpers.Helper;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.services.EmailService;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService
 {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
  

    @Override
    public User saveUser(User user) {
        // user id have to generate
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

       //profile pic bhi dena hai 
       //user.setProfilePic("userId");

       //User password encoding
       user.setPassword(passwordEncoder.encode(user.getPassword()));


       //set the user role
       //user.setRoleList(List.of(AppConstants.ROLE_USER));

       logger.info(user.getProviders().toString());

       // all data saved into this
       

       //verification token generate
       String emailToken = UUID.randomUUID().toString();
       user.setEmailToken(emailToken);

       User savedUser = userRepo.save(user);
       

       // Token pass to helper 
       String emailLink = Helper.getLinkForEmailVerification(emailToken);

       emailService.sendEmail(savedUser.getEmail(),"verify Account : Email Contact Manager", emailLink);

       return savedUser;

    }

    @Override
    public Optional<User> getUserById(String id) {
       return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
       //User user2 = userRepo.findById(user.getUserId()).orElseThrow()-> new ResourceNotFoundException("User not found");
        User user2 = userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found") );
        //Update user2 from user
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProviders(user.getProviders());
        user2.setProviderId(user.getProviderId());

        //Save the user in data base
        User saveUser = userRepo.save(user2);

        return Optional.ofNullable(saveUser);

    }

    @Override
    public void deleteUser(String id) {
       User user2 = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("user not found"));
       userRepo.delete(user2);
    }

    @Override
    public boolean iseUserExist(String userId) {
        User user2 = userRepo.findById(userId).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
       User user = userRepo.findByEmail(email).orElse(null);
       return user != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        // for all data fetch
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
      
      return userRepo.findByEmail(email).orElse(null);
    }

}
