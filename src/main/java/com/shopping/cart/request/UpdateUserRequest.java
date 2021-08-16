package com.shopping.cart.request;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class UpdateUserRequest {

    @Pattern(regexp = "^[A-Z][a-z]+$", message = "First name does not match pattern.")
    private String firstName;

    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Last name does not match pattern.")
    private String lastName;

}
