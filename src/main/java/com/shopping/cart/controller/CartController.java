package com.shopping.cart.controller;

import com.shopping.cart.dto.CartDTO;
import com.shopping.cart.request.AddToCartRequest;
import com.shopping.cart.request.UpdateCartItemRequest;
import com.shopping.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("shopping-cart/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MANAGE_CART')")
    public ResponseEntity<CartDTO> fetchCart() {
        return ResponseEntity.ok(cartService.fetchCart());
    }

    @PostMapping("/add-product")
    @PreAuthorize("hasAuthority('MANAGE_CART')")
    public ResponseEntity<CartDTO> addProduct(@RequestBody @Valid AddToCartRequest request) {
        return ResponseEntity.ok(cartService.addProduct(request.getProductID(), request.getQuantity()));
    }

    @PutMapping("/update-cart-item")
    @PreAuthorize("hasAuthority('MANAGE_CART')")
    public ResponseEntity<CartDTO> updateCartItem(@RequestBody @Valid UpdateCartItemRequest request) {
        return ResponseEntity.ok(cartService.updateCartItem(request.getCartItemId(), request.getQuantity()));
    }

    @DeleteMapping("/delete-cart-item/{cartItemId}")
    @PreAuthorize("hasAuthority('MANAGE_CART')")
    public ResponseEntity<CartDTO> deleteCartItem(@PathVariable("cartItemId") Long id) {
        return ResponseEntity.ok(cartService.deleteCartItem(id));
    }

    @DeleteMapping("/empty")
    @PreAuthorize("hasAuthority('MANAGE_CART')")
    public ResponseEntity<HttpStatus> emptyCart() {
        cartService.emptyCart();
        return ResponseEntity.ok().build();
    }

}
