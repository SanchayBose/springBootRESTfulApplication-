package com.hands_on.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hands_on.model.Employee;


public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	public Optional<Employee> findById(Long id);
}