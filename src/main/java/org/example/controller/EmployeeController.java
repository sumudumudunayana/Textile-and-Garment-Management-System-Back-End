package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.Employee;
import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class EmployeeController {
    @Autowired
    final EmployeeService service;

    @GetMapping("/get-all")
    public List<Employee> getEmployee(){
        return service.getAll();
    }

    @PostMapping("/add-employee")
    public void addCustomer(@RequestBody Employee employee){
        log.info("updated-> {}",employee);
        service.addEmployee(employee);
    }

    @GetMapping("/search-by-id/{id}")
    public Employee getEmployeeById(@PathVariable Integer id){
        return service.searchEmployeeById(id);
    }

    @DeleteMapping("/delete-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteEmployeeById(@PathVariable Integer id){
        service.deleteEmployeeById(id);
    }
    @PutMapping("/update-employee")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateEmployee(@RequestBody Employee employee){
        service.updateEmployeeById(employee);
    }
}
