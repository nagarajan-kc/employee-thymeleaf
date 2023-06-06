package com.nic.employee.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.nic.employee.Entity.Employee;
import com.nic.employee.Repository.EmployeeRepository;
import com.nic.employee.designation.Designation;


@Service
public class EmployeeServiceImp implements EmployeeService {

	 @Autowired
	    private EmployeeRepository employeeRepository;
	    
	    @Autowired
	    private JdbcTemplate jdbcTemplate;
	    
	    private final String SQL_GET_ALL_EMPLOYEE = "Select e.id,e.name,e.dob,e.gender,e.salary,d.field From employee e Join designation d ON e.designation = d.code";

    @Override
    public List < Employee > getAllEmployees() {
//       String query = "Select employee.id,employee.name,employee.dob,employee.gender,employee.salary,designation.field From employee Join designation ON employee.designation = designation.code";
 
//    List<Employee> employee = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Employee.class)); 
    return jdbcTemplate.query(SQL_GET_ALL_EMPLOYEE, new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public void saveEmployee(Employee employee) {
       this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(String id) {
       Optional < Employee > optional = employeeRepository.findById(id);
       Employee employee = null;
       if (optional.isPresent()) {
           employee = optional.get();
            } else {
       throw new RuntimeException(" Employee not found for id :: " + id);
       }
            return employee;
}

    @Override
    public int deleteEmployeeById(String id) {
    	return jdbcTemplate.update("DELETE FROM Employee WHERE id=?", id);
}

	@Override
	public List<Designation> getAllDesignation() {
		String query ="SELECT * FROM designation";
        List<Designation> designation = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Designation.class));
        return designation;
	}

	@Override
	public List<Designation> findAll() {
		
		String query ="SELECT * FROM designation";
        List<Designation> designation = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Designation.class));
        return designation;
	}

	
}
