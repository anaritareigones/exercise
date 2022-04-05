package com.jumia.exercise.service;

import com.jumia.exercise.entity.Customer;
import com.jumia.exercise.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findWithFiltersPage(String country, String state, int page, int limit) {
        Pageable pageable = (Pageable) PageRequest.of(page,limit);
        List<Customer> allCustomers = customerRepository.findAll(pageable).getContent();
        System.out.println(allCustomers);
        if (country.equals("") && state.equals("")){
            return allCustomers;
        }
        if (!country.equals("")) {
            allCustomers = filterByCountry(allCustomers, country);
        }
        if (!state.equals("")) {
            allCustomers = filterByState(allCustomers, state);
        }
        return allCustomers;
    }
    public List<Customer> findWithFilters(String country, String state) {
        List<Customer> allCustomers = customerRepository.findAll();
        System.out.println(allCustomers);
        if (country.equals("") && state.equals("")){
            return allCustomers;
        }
        if (!country.equals("")) {
            allCustomers = filterByCountry(allCustomers, country);
        }
        if (!state.equals("")) {
            allCustomers = filterByState(allCustomers, state);
        }
        return allCustomers;
    }

    public List<Customer> filterByCountry(List<Customer> list, String country){
        List<Customer> result = new ArrayList<Customer>();

        for(int i=0; i<list.size(); i++) {
            if(list.get(i).getPhone().contains(countryToCode(country))) {
                result.add(list.get(i));
            }
        }

        return result;
    }

    public List<Customer> filterByState(List<Customer> list, String state){
        List<Customer> resultValid = new ArrayList<Customer>();
        List<Customer> resultInvalid = new ArrayList<Customer>();
        boolean isValid = false;

        for(int i=0; i<list.size(); i++) {
            isValid = validateNumber(list.get(i));
            if(isValid)
                resultValid.add(list.get(i));
            else resultInvalid.add(list.get(i));
        }

        return (state.toLowerCase().equals("valid")) ? resultValid : resultInvalid;
    }

    public String countryToCode(String country) {
        switch (country.toLowerCase()) {
            case "cameroon":
                return "(237)";
            case "ethiopia":
                return "(251)";
            case "morocco":
                return "(212)";
            case "mozambique":
                return "(258)";
            case "uganda":
                return "(256)";
            default:
                return "Invalid";
        }
    }

    public boolean validateNumber(Customer customer) {
        StringBuilder phone = new StringBuilder(customer.getPhone().substring(1,4));

        switch (phone.toString()) {
            case "237":
                return customer.getPhone().matches(" \\(237\\)\\ ?[2368]\\d{7,8}$\n");
            case "251":
                return customer.getPhone().matches("\\(251\\)\\ ?[1-59]\\d{8}$");
            case "212":
                return customer.getPhone().matches("\\(212\\)\\ ?[5-9]\\d{8}$\n");
            case "258":
                return customer.getPhone().matches("\\(258\\)\\ ?[28]\\d{7,8}$");
            case "256":
                return customer.getPhone().matches("\\(256\\)\\ ?\\d{9}$");
            default:
                return false;
        }

    }
}

