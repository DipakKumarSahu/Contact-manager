 package com.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;





@Controller
public class controller {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }
    @RequestMapping("/home")
    public String homePage(Model model){
        model.addAttribute("name","My Name is Dipak");
        model.addAttribute("Youtube","Learn Code with Durgesh");
        model.addAttribute("link", "www.facebook.com");

        return "home";

    }

    @RequestMapping("/about")
    public String aboutPage(Model model){
        model.addAttribute("isLogin", true);

        return "about";
    }

    @RequestMapping("/services")
        public String servicePage(){

            return "services";
        }

        //Contact Page
    @GetMapping("/contact")
        public String contactPage(){
            return "contact";
        }

    @GetMapping("/login")
    public String login(){
      //  return new String("login"); 
      return "login";
    }

       
    // @PostMapping("/login")
    // public String loginPage(){
    //     return "login";
    // }    
      

    //Registration Page
    @GetMapping("/register")
    public String register(Model model){

         UserForm userForm = new UserForm();
        // userForm.setName("Dipak Kumar Sahu");
        // userForm.setAbout("Ab about mein kya hi bolun");
        // userForm.setEmail("mynameis@name.com");
        // userForm.setPassword("Secret hai");
        // userForm.setPhoneNumber("6370940754");
        // //default data bhi daal sakte hein
         model.addAttribute("userForm", userForm);

        return"register";
    }

    //Processing Register.
    @RequestMapping(value = "/do-register", method=RequestMethod.POST)

    public String processregister(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession session) {
         //fetch Data
         //UserForm
        

         //validation form data
         if (bindingResult.hasErrors()) {
            return "register";
         }

       
        //recieve data in class

       
        //save to database

        // userFrom --> user

        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("images/user.png")
         // .build();

         User user = new User();
         user.setName(userForm.getName());
         user.setEmail(userForm.getEmail());
         user.setPassword(userForm.getPassword());
         user.setAbout(userForm.getAbout());
         user.setPhoneNumber(userForm.getPhoneNumber());
         user.setProfilePic("images/user.png");
         user.setEnabled(false);



        User savedUser = userService.saveUser(user);
        System.out.println("USER SAVED..............");


        //Create message 
       Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

       //add The message
       session.setAttribute("message",message);

     System.out.println(userForm);

        return "redirect:/register";
    }
    
}