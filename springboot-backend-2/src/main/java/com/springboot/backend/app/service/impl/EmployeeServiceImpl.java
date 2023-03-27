package com.springboot.backend.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.backend.app.exception.ResourceNotFoundException;
import com.springboot.backend.app.module.Employee;
import com.springboot.backend.app.repository.EmployeeRepository;
import com.springboot.backend.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	@Override
	public Employee saveEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	}
	
	@Override
	public Employee getEmployeeById(long id) {
	
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		}else{
//			throw new ResourceNotFoundException("Employee", "Id" ,id);
//		}
		
		
//		lamda Expression 
		return employeeRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Employee", "ID", id));
		
		
	}
	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
//		we need to check whether given employee ID is present in DB or not
		Employee existingEmployee = employeeRepository
				.findById(id)
				.orElseThrow(()->
		 
				new ResourceNotFoundException("Employee","Id",id));
		
		existingEmployee.setFname(employee.getFname());
		existingEmployee.setLname(employee.getLname());
		existingEmployee.setEmail(employee.getEmail());
		
//		Save existing employee to DB
		employeeRepository.save(existingEmployee);
		
		return existingEmployee;
	}
	
	
	@Override
	public void deleteEmployee(long id) {
		
//		check whether employee exist in DB or not
		employeeRepository.findById(id).orElseThrow(()->
					 new ResourceNotFoundException("Employee","Id",id));
		employeeRepository.deleteById(id );
		 
	}
	

}
