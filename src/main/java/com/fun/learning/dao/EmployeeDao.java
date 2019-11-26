package com.fun.learning.dao;

import java.util.List;

import com.fun.learning.pojo.Employee;

/**
 * Employee DAO interface.
 */
public interface EmployeeDao {

    List<Employee> findAll();
}
