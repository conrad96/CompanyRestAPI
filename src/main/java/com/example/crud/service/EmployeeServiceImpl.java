package com.example.crud.service;

import com.example.crud.entity.Employee;
import com.example.crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> fetchAllEmployees() {
        List<Employee> allEmployees = employeeRepository.findAll();
        return allEmployees;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        }
        return null;
    }

    @Override
    public Employee updateEmployeeById(Long id, Employee employee) {
        Optional<Employee> employee1 = employeeRepository.findById(id);

        if(employee1.isPresent()) {
            Employee original = employee1.get();

            if(Objects.nonNull( employee.getEmployeeName() ) && !"".equalsIgnoreCase(employee.getEmployeeName())) {
                original.setEmployeeName(employee.getEmployeeName());
            }

            if(Objects.nonNull( employee.getEmployeeSalary() ) && (employee.getEmployeeSalary() != 0)) {
                original.setEmployeeSalary(employee.getEmployeeSalary());
            }
            return employeeRepository.save(original);
        }

        return null;
    }

    @Override
    public String deleteDepartmentById(Long id) {

        if(employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);

            return "Employee deleted successfully";
        }
        return "Employee not found.";
    }
}
