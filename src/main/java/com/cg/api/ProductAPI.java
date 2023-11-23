package com.cg.api;


import com.cg.model.dto.product.ProductCreateReqDTO;
import com.cg.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute ProductCreateReqDTO productCreateReqDTO) {

        productService.create(productCreateReqDTO);


        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
