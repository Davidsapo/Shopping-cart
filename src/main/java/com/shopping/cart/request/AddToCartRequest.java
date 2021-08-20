package com.shopping.cart.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AddToCartRequest {

    @NotNull
    @Min(1)
    private Long productID;

    @NotNull
    @Min(1)
    private Integer quantity;

}
