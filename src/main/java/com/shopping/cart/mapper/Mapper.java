package com.shopping.cart.mapper;

import com.shopping.cart.dto.*;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.entity.User;
import com.shopping.cart.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    ProductGetDTO productToProductGetDTO(Product product);

    Product productPostDTOToProduct(ProductPostDTO postDTO);

    List<ProductGetDTO> productsToProductGetDTOs(List<Product> productList);

    CartItemDTO cartItemToCartItemDTO(CartItem cartItem);

    List<CartItemDTO> cartItemsToCartItemDTOs(List<CartItem> cartItemList);

    CartDTO cartToCartDTO(Cart cart);

    UserGetDTO personToPersonGetDto(User user);

    User personPostDTOToPerson(UserPostDTO postDTO);

    List<UserGetDTO> personsToPersonGetDTOs(List<User> userList);

}
