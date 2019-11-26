package com.fun.learning.dao;

import java.util.List;

import com.fun.learning.AbstractIntegrationTest;
import com.fun.learning.dao.DefaultEmployeeDao;
import com.fun.learning.dao.EmployeeDao;
import com.fun.learning.pojo.Employee;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Employee DAO tests.
 *
 * @author Igor Bolic
 */
public class DefaultEmployeeDaoTest extends AbstractIntegrationTest {

    private final EmployeeDao service = DefaultEmployeeDao.INSTANCE;

    public void testThatFindReturnsPersonList() {
        final List<Employee> employees = service.findAll();

        assertThat(employees, notNullValue());
        assertThat(employees.size(), is(4));
        assertThat(employees.get(2), notNullValue());
        assertThat(employees.get(2).getId(), is(3));
        assertThat(employees.get(2).getFirstName(), is("Ami"));
        assertThat(employees.get(2).getLastName(), is("Johnson"));
        assertThat(employees.get(2).getAddress(), is("123 Sullivan St"));
        assertThat(employees.get(2).getCity(), is("Boston"));
    }

    @Override
    protected String dataSet() {
        return "/db/data.xml";
    }
}