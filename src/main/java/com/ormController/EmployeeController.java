package com.ormController;

import java.net.http.HttpHeaders;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ormModel.Employee;
import com.ormRepository.EmployeeRepository;
import com.ormService.TranformToJsonString;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	TranformToJsonString tranformer;
	
	@PostMapping(value="/employee/saveEmployee")
	public @ResponseBody String addEmployee(@RequestBody Employee employee) {
		Employee e = new Employee();
		e.setEmployeeName(employee.getEmployeeName());
		e.setEmployeeDesignation(employee.getEmployeeDesignation());
		e.setEmployeeLocation(employee.getEmployeeLocation());
		employeeRepository.save(e);
		return "Saved successfully";
	}
	
	@GetMapping(value="/employee/getEmployeeById")
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody Object getEmployee(@RequestBody Employee employee) throws Exception {
		
        try {
    		Employee e = employeeRepository.findById(employee.getEmployeeId()).get();
            //return tranformer.convertObjectToJsonString(e);
    		return e;
        }
        catch(NoSuchElementException nse) {
			throw new ResourceNotFoundException("Resource not found for Id "+ employee.getEmployeeId());
        }
	}
	
	@GetMapping(value="/employee/getEmployeeByDesignation")
	public ResponseEntity<?> getEmployeeByDesignation(@RequestBody Employee employee) {
		List<Employee> employees = employeeRepository.findAllByEmployeeDesignation(employee.getEmployeeDesignation());
		
		if(employees.size() == 0) {
			return new ResponseEntity<>("No employee with this designation exists",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(employees,HttpStatus.OK);
	}
	
	@GetMapping(value="/employee/getAllEmployees")
	public @ResponseBody String getAllEmployee() throws Exception {
		List<Employee> employees = employeeRepository.findAll();
		return tranformer.convertAllToJsonString(employees);
	}
	
	@DeleteMapping(value="/employee/removeEmployeeById")
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String deleteEmployeeById(@RequestBody Employee employee) throws Exception {
		
		try {
			employeeRepository.deleteById(employee.getEmployeeId());
			return "Employee deleted successfully";
		}
		catch(EmptyResultDataAccessException nse){
			throw new ResourceNotFoundException("Resource not found for Id "+ employee.getEmployeeId());
		}
	}
	
	@GetMapping(value="/employee/getAllEmployeesByLocation")
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String getEmployeesByLocation(@RequestBody Employee employee) throws Exception {
		List<Employee> employees = employeeRepository.findAllByEmployeeLocation(employee.getEmployeeLocation());
		
		if(employees.size() == 0) {
			throw new ResourceNotFoundException("Resource not found for Location "+ employee.getEmployeeLocation());
		}
		return tranformer.convertAllToJsonString(employees);
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "Redirecting to login";
	}

	@GetMapping("/user")
	public String user() {
		return "Redirecting to login";
	}
	
}