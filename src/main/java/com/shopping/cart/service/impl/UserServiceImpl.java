package com.shopping.cart.service.impl;

import com.shopping.cart.dto.UserGetDTO;
import com.shopping.cart.dto.UserPostDTO;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.User;
import com.shopping.cart.exception.exceptions.NonUniqueValueException;
import com.shopping.cart.mapper.Mapper;
import com.shopping.cart.repository.UserRepository;
import com.shopping.cart.request.UpdateUserRequest;
import com.shopping.cart.service.UserService;
import com.shopping.cart.validator.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final IdValidator idValidator;

    private final Mapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, IdValidator idValidator, Mapper mapper) {
        this.userRepository = userRepository;
        this.idValidator = idValidator;
        this.mapper = mapper;
    }

    @Override
    public UserGetDTO addUser(UserPostDTO userPostDTO) {
        if (userRepository.existsByEmailIgnoreCase(userPostDTO.getEmail())) {
            throw new NonUniqueValueException("Person", "email", userPostDTO.getEmail());
        }
        User user = mapper.personPostDTOToPerson(userPostDTO);
        user.setCart(new Cart());
        return mapper.personToPersonGetDto(userRepository.save(user));
    }

    @Override
    public List<UserGetDTO> getAllUsers() {
        return mapper.personsToPersonGetDTOs(userRepository.findAll());
    }

    @Override
    public User getUser(Long id) {
        idValidator.validPersonId(id);
        return userRepository.getById(id);
    }

    @Transactional
    @Override
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
