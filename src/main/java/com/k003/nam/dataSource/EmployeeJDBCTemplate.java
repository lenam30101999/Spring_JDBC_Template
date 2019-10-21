package com.k003.nam.dataSource;

import com.k003.nam.core.EmployeeMapper;
import com.k003.nam.object.Employee;
import com.k003.nam.object.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Service
@Transactional
public class EmployeeJDBCTemplate implements EmployeeDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Employee> listEmployee() {
        String SQL_Query = "SELECT * FROM EMPLOYEE";
        SqlQuery<Employee> sqlQuery = new SqlQuery<Employee>() {
            @Override
            protected RowMapper<Employee> newRowMapper(Object[] objects, Map<?, ?> map) {
                return new EmployeeMapper();
            }
        };
        sqlQuery.setDataSource(dataSource);
        sqlQuery.setSql(SQL_Query);
        List<Employee> lists = sqlQuery.execute();
        return lists;
    }

    //Tao them employee
    @Override
    public void create(Employee employee) {
        String SQL_Query = "INSERT INTO EMPLOYEE VALUES(?,?,?,?) ";
        jdbcTemplateObject.update(SQL_Query,
                employee.getId_Employee(), employee.getFirstName_Employee(),
                employee.getLastName_Employee(),employee.getAddress_Employee());
        System.out.println("Created!");
        return;
    }

    //Update employee
    @Override
    public void update(int id, String address){
        String SQL_Query = "UPDATE EMPLOYEE SET ADDRESS = ? WHERE ID = ?";
        jdbcTemplateObject.update(SQL_Query, address, id);
        System.out.println("Update with ID = " + id);
    }

    //Delete employee
    @Override
    public void delete(Integer id){
        String SQL_Query = "DELETE FROM EMPLOYEE WHERE ID = ?";
        jdbcTemplateObject.update(SQL_Query,id);
        System.out.println("Deleted with ID = " + id);
    }

    //Search 1 employee
    @Override
    public Employee getEmployee(Integer id){
        String SQL_Query = "SELECT * FROM EMPLOYEE WHERE ID = ?";
        Employee employee = jdbcTemplateObject.queryForObject(
                SQL_Query, new Object[] {id}, new EmployeeMapper());
        return employee;

        /*SimpleJdbcCall jdbcCall = new
                SimpleJdbcCall(dataSource).withProcedureName("getRecord");

        SqlParameterSource in = new MapSqlParameterSource().addValue("in_ID", id);
        Map<String, Object> out = jdbcCall.execute(in);

        Employee employee = new Employee();
        employee.setId_Employee(id);
        employee.setFirstName_Employee((String) out.get("out_FIRST_NAME"));
        employee.setLastName_Employee((String) out.get("out_LAST_NAME"));
        employee.setAddress_Employee((String) out.get("out_ADDRESS"));
        return employee;*/
    }

    @Override
    public void print(){

        List<Employee> employees = this.listEmployee();
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee emp1, Employee emp2) {
                return emp1.getId_Employee() - emp2.getId_Employee();
            }
        });

        System.out.println("----List----");
        for (Employee employee : employees){
            System.out.printf("%-20d%-30s%-30s%-30s\n",
                    employee.getId_Employee(),
                    employee.getFirstName_Employee(),
                    employee.getLastName_Employee(),
                    employee.getAddress_Employee());
        }
    }

}
