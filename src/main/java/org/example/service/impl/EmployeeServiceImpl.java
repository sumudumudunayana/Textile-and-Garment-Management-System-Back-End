package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.Employee;
import org.example.entity.EmployeeEntity;
import org.example.repository.EmployeeRepository;
import org.example.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<Employee> getAll() {
        List<Employee> EmployeeArrayList = new ArrayList<>();
        repository.findAll().forEach(entity->{
            EmployeeArrayList.add(mapper.map(entity, Employee.class));
        });
        return EmployeeArrayList;
    }

    @Override
    public void addEmployee(Employee employee) {
        System.out.println(employee);
        repository.save(mapper.map(employee, EmployeeEntity.class));
    }

    @Override
    public void deleteEmployeeById(Integer id){
        repository.deleteById(id);
    }

    @Override
    public Employee searchEmployeeById(Integer id) {
        return mapper.map(repository.findById(id),Employee.class);


    }

    @Override
    public void updateEmployeeById(Employee employee) {
        repository.save(mapper.map(employee, EmployeeEntity.class));
    }
}
