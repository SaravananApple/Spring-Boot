package com.example.ems.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.service.EmployeeService;
import com.example.ems.service.EmployeeServiceImpl;
import com.example.ems.model.Employee;
import java.util.List;
import java.util.Optional;

@RestController
public class HomeRestController 
{

	@Autowired
	private EmployeeService empService;
	
	@CrossOrigin(origins = "http://localhost:4200/")
	@GetMapping("/employees")
	public List <Employee> allEmployees()
	{
		return empService.viewAllRecords();
	}
	
	@GetMapping("/employees/{id}")
	public Employee showEmployee(@PathVariable("id") int eId)
	{
		Employee e = empService.searchEmployee(eId).orElse(new Employee()) ;
		return e;
	}
	
	
//	@GetMapping("/employees/{id}")
//	public JSONArray  showEmployeeJSON(@PathVariable("id") int eId)
//	{
//		Employee e = empService.searchEmployee(eId).orElse(new Employee()) ;
//		JSONArray arr = new JSONArray();
//		arr.put(e.toString());
//		return arr;
//	}
//	
	
	
@PostMapping("/employee")
public Employee addEmployee( Employee emp)
{
	Optional<Employee> opt = empService.searchEmployee(emp.getEmpId());
	if(opt.isEmpty())
	{
		empService.addEmployee(emp);
		return emp;
	}
	else
	{
	
		return null;
	}

}

@PutMapping("/employee")
public Employee updateEmployee( Employee emp)
{
	Optional<Employee> opt = empService.searchEmployee(emp.getEmpId());
	if(opt.isPresent())
	{
		empService.addEmployee(emp);
		return emp;
	}
	else
	{
	
		return null;
	}

}


@DeleteMapping("/employee/{id}")
public Optional<Employee> deleteEmployee(@PathVariable("id") int eId)
{
	Optional<Employee> opt = empService.searchEmployee(eId);
	if(opt.isPresent())
	{
		empService.deleteEmployee(eId);
		return opt;
	}
	else
	{
	
		return null;
	}
}






	
	
	
	
}
