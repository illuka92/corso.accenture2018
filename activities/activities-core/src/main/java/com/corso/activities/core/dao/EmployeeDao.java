package com.corso.activities.core.dao;

import java.util.List;

import com.corso.activities.core.model.Employee;

public interface EmployeeDao {
	Employee findById(Long id);
	List<Employee> findAll();
	Employee findByFirstNameAndLastName(String firstName, String lastName);
}
