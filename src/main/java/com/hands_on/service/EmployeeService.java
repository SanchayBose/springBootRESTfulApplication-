package com.hands_on.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hands_on.dao.EmployeeRepository;
import com.hands_on.model.Employee;

@Component
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    
    private static List<Employee> list = new ArrayList<>();
    static {
         list.add(new Employee(121l, "Java ", "XYZ@gmAIL.COM","TRURYJR"));
         list.add(new Employee(363l, "Head", "ABC@GMAIL.COM","TIRYI"));
         list.add(new Employee(84l, "Thing", "LMN@FF.COM","RETRTR"));
     }
    
    
    // get details of all registered employees
    public List<Employee> getAllEmployees() {
    	
        List<Employee> list=(List<Employee>)this.employeeRepository.findAll();
        return list;
    }
    

    // get single employee by id
    public Optional<Employee> getEmployeeById(Long id) {
    	
    	Optional<Employee> employee = null;
    	
        try {
            // employee = list.stream().filter(e -> e.getId() == id).findFirst().get();
        	employee=this.employeeRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }
    

    // add the employee details
    public Employee addEmployee(Employee b) {
    	
    	Employee result=employeeRepository.save(b);
        return result;
    }
    

    // delete the unwanted Employee record
    public void deleteEmployee(Long bid) {

    	employeeRepository.deleteById(bid);
    }

    
    // update the employee details
    public void updateEmployee(Employee employee, Long employeeId) {

    	employee.setId(employeeId);
        employeeRepository.save(employee);
    }

}