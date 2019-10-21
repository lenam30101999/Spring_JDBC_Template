package com.k003.nam.object;

import javax.sql.DataSource;
import java.util.List;

public interface EmployeeDAO {

    public void setDataSource(DataSource dataSource);

    public void create(Employee employee);

    public void update(int id, String address);

    public void delete(Integer id);

    public void print();

    public Employee getEmployee(Integer id);

    public List<Employee> listEmployee();

}
