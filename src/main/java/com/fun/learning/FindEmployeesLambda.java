package com.fun.learning;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fun.learning.dao.DefaultEmployeeDao;
import com.fun.learning.dao.EmployeeDao;
import com.fun.learning.pojo.Employee;

import java.util.List;

/**
 * AWS Lambda function retrieving the list of {@link Employee}s.
 *
 * @author Igor Bolic
 */
public class FindEmployeesLambda implements RequestHandler<Void, List<Employee>> {

    private final EmployeeDao employeeDao = DefaultEmployeeDao.INSTANCE;

    @Override
    public List<Employee> handleRequest(Void input, Context context) {
        return employeeDao.findAll();
    }
}
