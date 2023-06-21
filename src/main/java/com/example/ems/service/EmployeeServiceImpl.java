package com.example.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ems.model.Employee;
import com.example.ems.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService
{

@Autowired
EmployeeRepository employeeRepo;
	
	@Override
	public Employee addEmployee(Employee obj) {
		
		return employeeRepo.save(obj);
	}

	
	@Override
	public Optional<Employee> searchEmployee(Integer id) {
	
		return employeeRepo.findById(id);
	}


	@Override
	public String deleteEmployee(Integer id) 
	{
		
		employeeRepo.deleteById(id);
		return id+" "+"RECORD IS DELETED";
	}


	@Override
	public List<Employee> viewAllRecords() 
	{
	
		return  (List<Employee>) employeeRepo.findAll();
	}



}
