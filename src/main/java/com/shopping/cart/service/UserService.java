package com.shopping.cart.service;

import com.shopping.cart.dto.UserGetDTO;
import com.shopping.cart.dto.UserPostDTO;
import com.shopping.cart.entity.User;
import com.shopping.cart.request.UpdateUserRequest;

import java.util.List;

public interface UserService {

    UserGetDTO registerNewUser(UserPostDTO userPostDTO);

    List<UserGetDTO> getAllUsers();

    User getLoggedInUser();

    UserGetDTO getLoggedInUserDTO();

    UserGetDTO updateUser(Long id, UpdateUserRequest updateUserRequest);

    void deleteUser(Long id);

}
