package com.shopping.cart.validator;

import com.shopping.cart.exception.exceptions.IdException;
import com.shopping.cart.repository.UserRepository;
import com.shopping.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdValidator {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    @Autowired
    public IdValidator(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public void validProductId(Long productId) {
        validId(productId);
        if (!productRepository.existsById(productId)) {
            throw IdException.noElementWithId("Product", productId);
        }
    }

    public void validPersonId(Long personId) {
        validId(personId);
        if (!userRepository.existsById(personId)) {
            throw IdException.noElementWithId("Person", personId);
        }
    }

    private void validId(Long id) {
        if (id < 0) {
            throw IdException.negativeId();
        }
    }
}
