package com.shopping.cart.service.impl;

import com.shopping.cart.dto.UserGetDTO;
import com.shopping.cart.dto.UserPostDTO;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.User;
import com.shopping.cart.enums.Role;
import com.shopping.cart.exception.exceptions.NonUniqueValueException;
import com.shopping.cart.mapper.Mapper;
import com.shopping.cart.repository.UserRepository;
import com.shopping.cart.request.UpdateUserRequest;
import com.shopping.cart.service.UserService;
import com.shopping.cart.validator.IdValidator;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final IdValidator idValidator;

    private final Mapper mapper;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, IdValidator idValidator, Mapper mapper,
                           @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.idValidator = idValidator;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserGetDTO registerNewUser(UserPostDTO userPostDTO) {
        if (userRepository.existsByEmailIgnoreCase(userPostDTO.getEmail())) {
            throw new NonUniqueValueException("Person", "email", userPostDTO.getEmail());
        }
        User user = mapper.personPostDTOToPerson(userPostDTO);
        user.setCart(new Cart());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.personToPersonGetDto(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserGetDTO> getAllUsers() {
        return mapper.personsToPersonGetDTOs(userRepository.findAll());
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public User getLoggedInUser() {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userRepository.getByEmail(email);
    }

    @Override
    public UserGetDTO getLoggedInUserDTO() {
        return mapper.personToPersonGetDto(getLoggedInUser());
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UserGetDTO updateUser(Long id, UpdateUserRequest updateUserRequest) {
        idValidator.validPersonId(id);
        User user = userRepository.getById(id);
        String firstName = updateUserRequest.getFirstName();
        String lastName = updateUserRequest.getLastName();
        if (Objects.nonNull(firstName)) {
            user.setFirstName(firstName);
        }
        if (Objects.nonNull(lastName)) {
            user.setLastName(lastName);
        }
        return mapper.personToPersonGetDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        idValidator.validPersonId(id);
        userRepository.deleteById(id);
    }

}
