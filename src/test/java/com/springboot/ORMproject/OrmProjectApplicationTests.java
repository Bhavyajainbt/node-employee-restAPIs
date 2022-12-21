package com.springboot.ORMproject;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.ormController.EmployeeController;
import com.ormModel.Employee;
import com.ormRepository.EmployeeRepository;

import io.jsonwebtoken.lang.Assert;

@SpringBootTest
class OrmProjectApplicationTests {
	
	@Mock
	private EmployeeRepository repository;

	@InjectMocks
	private EmployeeController controller;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void getAllEmployeeTest() {
		
		List<Employee> actualEmployees = new ArrayList<>();
		
		Employee e1 = new Employee();
		Employee e2 = new Employee();
		
		e1.setEmployeeId(41);
		e1.setEmployeeName("Ravish kumar");
		e1.setEmployeeLocation("Bhatinda");
		e1.setEmployeeDesignation("Sales person");
		
		e2.setEmployeeId(42);
		e2.setEmployeeName("Himanshu dhuliya");
		e2.setEmployeeLocation("Panvel");
		e2.setEmployeeDesignation("sales consultant");
		
		actualEmployees.add(e1);
		actualEmployees.add(e2);
		
		Mockito.when(repository.findAll()).thenReturn(actualEmployees);
		
		Assert.isTrue(actualEmployees.size() == 2);
		
		
	}

}
