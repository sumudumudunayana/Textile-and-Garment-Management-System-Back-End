package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.Customer;
import org.example.entity.CustomerEntity;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<Customer> getAll() {
        List<Customer> CustomerArrayList = new ArrayList<>();
        repository.findAll().forEach(entity->{
            CustomerArrayList.add(mapper.map(entity, Customer.class));
        });
        return CustomerArrayList;
    }

    @Override
    public void addCustomer(Customer customer) {
        System.out.println(customer);
        repository.save(mapper.map(customer, CustomerEntity.class));
    }

    @Override
    public void deleteCustomerById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Customer searchCustomerById(Integer id) {
        return mapper.map(repository.findById(id),Customer.class);
    }

    @Override
    public void updateCustomerById(Customer customer) {
        repository.save(mapper.map(customer, CustomerEntity.class));
    }
}
