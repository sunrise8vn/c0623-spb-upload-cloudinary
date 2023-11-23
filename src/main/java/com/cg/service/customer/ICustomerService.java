package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.dto.CustomerResDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer, Long> {

    List<Customer> findAllByIdNot(Long id);

    List<CustomerResDTO> findAllCustomerResDTO();

    void create(Customer customer);

}
