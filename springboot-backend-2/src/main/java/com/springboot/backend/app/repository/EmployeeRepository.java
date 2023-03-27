package com.springboot.backend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.backend.app.module.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{

}
