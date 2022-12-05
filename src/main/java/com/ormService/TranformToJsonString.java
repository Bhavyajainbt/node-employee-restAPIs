package com.ormService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONObject;
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
		
		//List<Employee> e2 = employees.stream().filter(p-> p.getEmployeeLocation().toLowerCase().equals("pune"))
			//			.collect(Collectors.toList());
	
		List<Employee> e2 = new ArrayList<Employee>();
		
		// Sorted according to employee name
		employees.stream().sorted((x1,x2)-> x1.getEmployeeName().compareTo(x2.getEmployeeName()))
		.forEach(p -> e2.add(p));
		
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(e2);
		return jsonStr;
	}
}
