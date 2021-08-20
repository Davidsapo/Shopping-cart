package com.shopping.cart.dto;

import com.shopping.cart.enums.Role;
import lombok.Data;

@Data
public class UserGetDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    private CartDTO cart;
}
