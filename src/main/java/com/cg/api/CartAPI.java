package com.cg.api;


import com.cg.model.User;
import com.cg.model.dto.cart.CartReqDTO;
import com.cg.service.cart.ICartService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
public class CartAPI {

    @Autowired
    private ICartService cartService;

    @Autowired
    private AppUtils appUtils;

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody CartReqDTO cartReqDTO) {

        String username = appUtils.getPrincipalUsername();

        cartService.addToCart(cartReqDTO, username);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
