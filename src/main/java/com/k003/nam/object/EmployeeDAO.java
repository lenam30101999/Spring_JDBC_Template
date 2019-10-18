package com.k003.nam.object;

import javax.sql.DataSource;
import java.util.List;

public interface EmployeeDAO {

    public void setDataSource(DataSource dataSource);

    //public void create(int id, String firstName, String lastName, String address);

    public List<Employee> listEmployee();
}
