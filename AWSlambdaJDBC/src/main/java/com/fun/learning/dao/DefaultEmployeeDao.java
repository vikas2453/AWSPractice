package com.fun.learning.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fun.learning.db.Database;
import com.fun.learning.pojo.Employee;

/**
 * Default employee DAO implementation.
 */
public enum DefaultEmployeeDao implements EmployeeDao {
    INSTANCE;

    @Override
    public List<Employee> findAll() {
        final List<Employee> employees = new ArrayList<>();

        try (Connection conn = Database.connection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM employee")) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee employee = Employee.of(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("city"));

                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
