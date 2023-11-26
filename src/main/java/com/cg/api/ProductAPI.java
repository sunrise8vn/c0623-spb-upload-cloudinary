package com.cg.api;


import com.cg.model.dto.product.ProductAvatarResDTO;
import com.cg.model.dto.product.ProductCreateReqDTO;
import com.cg.model.dto.product.ProductResDTO;
import com.cg.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<?> getAll() {

        List<ProductResDTO> productResDTOS = productService.getAllProductResDTO();

        return new ResponseEntity<>(productResDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute ProductCreateReqDTO productCreateReqDTO) {

        productService.create(productCreateReqDTO);


        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
