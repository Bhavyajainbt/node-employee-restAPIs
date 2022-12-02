package com.ormService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ormModel.Employee;

@Service
public class TranformToJsonString {
	
	public String convertObjectToJsonString(Employee employee) throws Exception {
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(employee);
		return jsonStr;
	}
	
	public String convertAllToJsonString(List<Employee> employees) throws Exception {
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(employees);
		return jsonStr;
	}
}
