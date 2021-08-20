package com.shopping.cart.controller;

import com.shopping.cart.dto.UserGetDTO;
import com.shopping.cart.dto.UserPostDTO;
import com.shopping.cart.request.UpdateUserRequest;
import com.shopping.cart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("shopping-cart/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserGetDTO> register(@RequestBody @Valid UserPostDTO userPostDTO) {
        return ResponseEntity.ok(userService.registerNewUser(userPostDTO));
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('READ_USERS')")
    @Transactional(readOnly = true)
    public ResponseEntity<List<UserGetDTO>> list() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    public ResponseEntity<UserGetDTO> update(@PathVariable("id") Long id, @RequestBody @Valid UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.updateUser(id, updateUserRequest));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully.");
    }
}
