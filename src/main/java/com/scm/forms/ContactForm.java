package com.scm.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactForm {

    @NotBlank(message="Name is required")
    private String name;

    @Email(message = "Invalid Email Address")
    @NotBlank(message="Email is required")
    private String email;

    @NotBlank(message="Phone Number is required")   
    @Pattern(regexp = "^\\d{10}$", message = "Invalid Phone Number")
    private String phoneNumber;

    @NotBlank(message="Address is required")
    @Size(min = 5, message="Minimum 5 character is required")
    private String address;

    
    private String description;


    private boolean favorite = false;


    private String websiteLink;


    private String linkedInLink;


    //custom validator create karenge(ek annotation create karenge jo file ko validate karega)
    //size, resolution
    private MultipartFile contactImage;

    private String picture;

}


// Info about Pattern

// @Pattern(...)
// Ye annotation batata hai ki String input ko ek regular expression ke against match karna hai.

// regexp = "^\\d{10}$"
// Ye ek regular expression (regex) hai:

// ^ → Start of string

// \\d{10} → Exactly 10 digits (\\d ka matlab hota hai "digit", {10} ka matlab "10 baar")

// $ → End of string

// 🟢 Matlab: String mein sirf aur sirf 10 digits hone chahiye, bina kisi space, +91, ya dash ke.

// message = "Invalid Phone Number"