package com.hands_on.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hands_on.model.Employee;
import com.hands_on.service.EmployeeService;

@RestController
@EnableAutoConfiguration
@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	//---------------------------------------CONDITION 1---------------------------------------
	
	// get details of all registered employees handler
	@GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {

        List<Employee> list = employeeService.getAllEmployees();
        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
	
	
	// get single employee by id handler
    @GetMapping("/employee/{id}")
    public ResponseEntity<Optional<Employee>> getBook(@PathVariable("id") Long id) {
    	Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(employee);
    }
	
    
	//---------------------------------------CONDITION 2---------------------------------------
	
    // save an Employee`s details handler
    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
    	Employee b = null;
        try {
            b = this.employeeService.addEmployee(employee);
            System.out.println(b);
            return ResponseEntity.status(HttpStatus.CREATED).body(employee);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }
    
	
	//---------------------------------------CONDITION 3---------------------------------------
    
    // update/modify the Employee`s details except for Employee ID handler
    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("employeeId") Long employeeId) {
        try {
            this.employeeService.updateEmployee(employee, employeeId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    
    
    //---------------------------------------CONDITION 4---------------------------------------
    
    // delete an unwanted Employee record handler

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        try {
            this.employeeService.deleteEmployee(employeeId);
            return ResponseEntity.status(HttpStatus.GONE).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }
    
}
