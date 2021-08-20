package com.shopping.cart.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserPostDTO {

    @NotNull(message = "First name can not be empty.")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "First name does not match pattern.")
    private String firstName;

    @NotNull(message = "Last name can not be empty.")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Last name does not match pattern.")
    private String lastName;

    @NotNull(message = "Email required")
    @Email(message = "Invalid email address.")
    private String email;

    @NotNull(message = "Password required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$)[a-zA-Z1-9]{4,8}$", message = "Password must contains at least one letters and digit and must be at least 4 characters.")
    private String password;

}
