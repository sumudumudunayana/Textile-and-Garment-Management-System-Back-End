package org.example.service;

import org.example.dto.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();

    void addCustomer(Customer customer);

    void deleteCustomerById(Integer id);

    Customer searchCustomerById(Integer id);

    void updateCustomerById(Customer customer);
}
