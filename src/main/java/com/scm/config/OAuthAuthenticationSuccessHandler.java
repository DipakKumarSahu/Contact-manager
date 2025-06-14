package com.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.entities.Providers;
import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private final DaoAuthenticationProvider authenticationProvider;
  Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

  @Autowired
  private UserRepo userRepo;

  @Lazy
  OAuthAuthenticationSuccessHandler(DaoAuthenticationProvider authenticationProvider) {
    this.authenticationProvider = authenticationProvider;
  }

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    logger.info("OAuthAuthenticationSuccessHandler");

    // agar google se login karte ho to ye console mein data print karegi
    // logger.info(user.getName());

    // user.getAttributes().forEach((key,value)->{
    // logger.info("{} | {}",key, value);
    // });

    // logger.info(user.getAuthorities().toString());

    // identify the provider
    var oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

    String authorizedClientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

    logger.info(authorizedClientRegistrationId);
    // data fetch
    var oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
    oauthUser.getAttributes().forEach((key, value) -> {
      logger.info(key + "  " + value);
    });

    User user = new User();
    user.setUserId(UUID.randomUUID().toString());
    user.setRoleList(List.of(AppConstants.ROLE_USER));
    user.setEmailVerified(true);
    user.setEnabled(true);
    user.setPassword("Dummy Password");
    if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {
      // google
      // google attributes
      user.setEmail(oauthUser.getAttribute("email").toString());
      user.setProfilePic(oauthUser.getAttribute("picture").toString());
      user.setName(oauthUser.getAttribute("name").toString());
      user.setProviderId(oauthUser.getName());
      user.setProviders(Providers.GOOGLE);
      user.setAbout("This Account is created using Google");
    }
    else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {
      // github
      // github attributes

      // here if we get the user email then we will store in db or if we dont get
      // email then we fetch the username and concatnate the string = @gmail.com
      String email = oauthUser.getAttribute("email") != null ? oauthUser.getAttribute("email").toString()
          : oauthUser.getAttribute("login").toString() + "@gmail.com";
      String picture = oauthUser.getAttribute("avatar_url").toString();
      String name = oauthUser.getAttribute("login").toString();
      String providerId = oauthUser.getName();

      user.setEmail(email);
      user.setProfilePic(picture);
      user.setName(name);
      user.setProviderId(providerId);
      user.setProviders(Providers.GITHUB);
      user.setAbout("This Account is created using Github");

    } else if (authorizedClientRegistrationId.equalsIgnoreCase("linkedin")) {

      // Linked In
    }

    else {
      logger.info("OAuthAuthenticationSuccessHandler :  Unknown Provider");
    }

    // save the user

    // Before redirect we will save data in data base

    /*
     * This for only Google data to save in Data base
     * DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
     * String email = user.getAttribute("email").toString();
     * String name = user.getAttribute("name").toString();
     * String picture = user.getAttribute("picture").toString();
     * 
     * //Create User and save into database
     * 
     * User user1 = new User();
     * user1.setEmail(email);
     * user1.setName(name);
     * user1.setProfilePic(picture);
     * user1.setPassword("password");
     * user1.setUserId(UUID.randomUUID().toString());
     * user1.setProviders(Providers.GOOGLE);
     * user1.setEnabled(true);
     * user1.setEmailVerified(true);
     * user1.setProviderId(user.getName());
     * user1.setRoleList(List.of(AppConstants.ROLE_USER));
     * user1.setAbout("This Account is created using google");
     * 
     * 
     * User user2 = userRepo.findByEmail(email).orElse(null);
     * if(user2 == null){
     * userRepo.save(user1);
     * logger.info("User Saved "+email);
     * }
     */
    User user2 = userRepo.findByEmail(user.getEmail()).orElse(null);
    if (user2 == null) {
      userRepo.save(user);
      logger.info("User Saved " + user.getEmail());
    }
    new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
  }
}
