package com.cg.api;


import com.cg.exception.DataInputException;
import com.cg.model.Customer;
import com.cg.model.dto.*;
import com.cg.service.customer.ICustomerService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<?> getALl() {
        List<CustomerResDTO> customerResDTOS = customerService.findAllCustomerResDTO();

        return new ResponseEntity<>(customerResDTOS, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getById(@PathVariable Long customerId) {

        Customer customer = customerService.findById(customerId).orElseThrow(() -> {
            throw new DataInputException("Customer not found");
        });

        CustomerResDTO customerResDTO = customer.toCustomerResDTO();


        return new ResponseEntity<>(customerResDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody CustomerCreReqDTO customerCreReqDTO, BindingResult bindingResult) {

        new CustomerCreReqDTO().validate(customerCreReqDTO, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Customer customer = customerCreReqDTO.toCustomer();
        customer.setDeleted(false);

        customerService.create(customer);

        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

}
