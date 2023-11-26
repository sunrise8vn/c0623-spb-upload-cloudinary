package com.cg.service.cart;

import com.cg.model.Cart;
import com.cg.model.dto.cart.CartReqDTO;
import com.cg.service.IGeneralService;

public interface ICartService extends IGeneralService<Cart, Long> {

    void addToCart(CartReqDTO cartReqDTO, String username);
}
