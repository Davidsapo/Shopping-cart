package com.shopping.cart.service;

import com.shopping.cart.dto.UserGetDTO;
import com.shopping.cart.dto.UserPostDTO;
import com.shopping.cart.entity.User;
import com.shopping.cart.request.UpdateUserRequest;

import java.util.List;

public interface UserService {

    UserGetDTO addUser(UserPostDTO userPostDTO);

    List<UserGetDTO> getAllUsers();

    User getUser(Long id);

    UserGetDTO updateUser(Long id, UpdateUserRequest updateUserRequest);

    void deleteUser(Long id);
}
