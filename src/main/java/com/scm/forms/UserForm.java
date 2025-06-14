package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class UserForm {

    @NotBlank(message = "User name is required")
    @Size(min = 3, message = "minimum 3 character is required")
    private String name;

    @NotBlank(message = "Email is required")
    // @Pattern("") you can also use email patterns
    @Email(message = "Invalid Email Address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message="Minimum 6 character is required")
    private String password;

    @NotBlank
    private String about;

    @Size(min = 8, max = 12, message = "Invalid Phone number")
    private String phoneNumber;

}
