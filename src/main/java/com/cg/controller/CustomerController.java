package com.cg.controller;

import com.cg.model.*;
import com.cg.service.customer.ICustomerService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public String showListPage(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);

        String username = appUtils.getPrincipalUsername();

        model.addAttribute("username", username);

        return "customer/list";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("customer", new Customer());

        return "customer/create";
    }

    @GetMapping("/edit/{customerId}")
    public String showUpdatePage(@PathVariable Long customerId, Model model) {
        Optional<Customer> customer = customerService.findById(customerId);
        model.addAttribute("customer", customer.get());

        return "customer/edit";
    }

    @GetMapping("/delete/{customerId}")
    public String showCreatePage(@PathVariable Long customerId, Model model) {
//        Customer customer = customerService.findById(customerId);
//        model.addAttribute("customer", customer);

        return "customer/delete";
    }

    @PostMapping("/create")
    public String createCustomer(@ModelAttribute Customer customer, Model model) {

        if (customer.getFullName().length() == 0) {
            model.addAttribute("success", false);
            model.addAttribute("message", "Created unsuccessful");
        }
        else {
            customerService.save(customer);

            model.addAttribute("customer", new Customer());

            model.addAttribute("success", true);
            model.addAttribute("message", "Created successfully");
        }

        return "customer/create";
    }

    @PostMapping("/edit/{customerId}")
    public String updateCustomer(@PathVariable Long customerId, @ModelAttribute Customer customer, Model model) {

        customer.setId(customerId);

        customerService.save(customer);

        model.addAttribute("success", true);
        model.addAttribute("message", "Updated successful");

        model.addAttribute("customer", customer);

        return "customer/edit";
    }

    @PostMapping("/delete/{customerId}")
    public String deleteCustomer(@PathVariable Long customerId, RedirectAttributes redirectAttributes) {

        customerService.deleteById(customerId);

        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("message", "Deleted successfully");

        return "redirect:/customers";
    }

}
