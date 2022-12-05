package com.ormRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ormModel.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	List<Employee> findAllByEmployeeLocation(String employeeLocation);
	
	@Query(value = "Select *from employee e where e.employee_designation = ?1 order by employee_name",nativeQuery=true)
	List<Employee> findAllByEmployeeDesignation(String employeeDesignation);
}
