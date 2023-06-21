package com.example.ems.controller;

import java.util.Optional;		

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ems.model.Employee;
import com.example.ems.service.EmployeeServiceImpl;



@Controller
public class HomeController {

@Autowired
EmployeeServiceImpl empService;
	
String msg = "";
	
@GetMapping("/")
public String homePage() {
	
	return "index";
	
}

@GetMapping("/add")
public String addPage(Model model) {
	
 Employee m = new Employee();
 model.addAttribute("empObj", m);

	
	
	return "add";
}

@PostMapping("/add")
public String addData(@ModelAttribute("empObj") Employee eObj) {
	
	Optional<Employee> opt = empService.searchEmployee(eObj.getEmpId());
	if(opt.isEmpty())
	{
		empService.addEmployee(eObj);
		msg = "ADDED SUCCESSFULLY";
		return "redirect:/message/"+msg;
	}
	else
	{
		msg = "RECORD ALREADY EXISTS";
		return "redirect:/message/"+msg;
	}
	
	 
	 
}

@GetMapping("/message/{msg}")
public String messagePage(@PathVariable("msg") String m, Model model)
{
	
	model.addAttribute("msgAlert", m);
	return "message";
	
}

@GetMapping("/update")
public String updatePage(Model model)
{
	Employee emp = new Employee();
	model.addAttribute("empObj", emp);
	return "update";
	
}

@PostMapping("/search")
public String searchData(@ModelAttribute("empObj") Employee obj, Model model)
{
	
	Optional<Employee> opt = empService.searchEmployee(obj.getEmpId());
	if(opt.isPresent())
	{
		obj.setStatus(true);
		model.addAttribute("record", opt);
		return "update";
	}
	else
	{
		msg = "RECORD NOT EXISTS";
		return "redirect:/message/"+msg;
	}
	
}

@PostMapping("/update")
public String updatePage(@ModelAttribute("record") Employee emp)
{
	
	empService.addEmployee(emp);
	msg = "UPDATED SUCESSFULLY";
	return "redirect:/message/"+msg;
	
}

@GetMapping("/delete")
public String deletePage(Model model)
{
	Employee emp = new Employee();
	model.addAttribute("empObj", emp);
	return "delete";
	
}

@PostMapping("/delete")
public String deleteData(@ModelAttribute("empObj") Employee emp)
{
	Optional<Employee> opt = empService.searchEmployee(emp.getEmpId());
	if(opt.isPresent())
	{
		msg = empService.deleteEmployee(emp.getEmpId());
		return "redirect:/message/"+msg;
	}
	else
	{
		msg = "RECORD NOT EXISTS";
		return "redirect:/message/"+msg;
	}
}


@GetMapping("/view")
public String viewPage(Model model)
{
	model.addAttribute("allEmployees", empService.viewAllRecords());
	return "view";
	
}








}
