package com.example.ems.service;

import java.util.List;	
import java.util.Optional;

import com.example.ems.model.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee obj);
	
	Optional<Employee> searchEmployee(Integer id);
	
	String deleteEmployee(Integer id);
	
	List<Employee> viewAllRecords();
	
	
	

	
}