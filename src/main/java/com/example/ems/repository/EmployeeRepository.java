package com.example.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import com.example.ems.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>
{

}
