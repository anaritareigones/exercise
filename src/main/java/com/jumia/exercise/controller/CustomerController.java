package com.jumia.exercise.controller;

import com.jumia.exercise.entity.Customer;
import com.jumia.exercise.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping(value="getCustomerByOptional", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getCustomerByOptional(@RequestParam(value="country", required = false, defaultValue = "") String country,
                                              @RequestParam(value="state", required = false, defaultValue = "") String state){

        List<Customer> customers = customerService.findWithFilters(country, state);
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @RequestMapping(value="getCustomerPage", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getCustomerPage(@RequestParam(value="country", required = false, defaultValue = "") String country,
                                                                @RequestParam(value="state", required = false, defaultValue = "") String state,
                                                                @RequestParam(value="page", defaultValue = "0") Integer page,
                                                                @RequestParam(value = "limit", defaultValue = "5") Integer limit){

        List<Customer> customers = customerService.findWithFiltersPage(country, state, page, limit);
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }
}
