package com.shopping.cart.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCartItemRequest {

    @NotNull
    @Min(1)
    private Long cartItemId;

    @NotNull
    @Min(1)
    private Integer quantity;
}
