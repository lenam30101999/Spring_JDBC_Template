package com.k003.nam.dataSource;

import com.k003.nam.core.EmployeeMapper;
import com.k003.nam.object.Employee;
import com.k003.nam.object.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class EmployeeJDBCTemplate implements EmployeeDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    //SimpleJdbcInsert jdbcInsert;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Employee> listEmployee() {
        String SQL_Query = "SELECT * FROM EMPLOYEE";
        List<Employee> lists = jdbcTemplateObject.query(SQL_Query,
                new ResultSetExtractor<List<Employee>>() {

                    public List<Employee> extractData(
                            ResultSet resultSet) throws SQLException, DataAccessException {

                        List<Employee> list = new ArrayList<Employee>();
                        while (resultSet.next()) {
                            Employee emp = new Employee();
                            emp.setId_Employee(resultSet.getInt("ID"));
                            emp.setFirstName_Employee(resultSet.getString("FIRST_NAME"));
                            emp.setLastName_Employee(resultSet.getString("LAST_NAME"));
                            emp.setAddress_Employee(resultSet.getString("ADDRESS"));
                        }

                        return list;
                    }
                });
        return lists;
    }

    //Tao them employee
    @Override
    public void create(int id, String firstName, String lastName, String address) {
        String SQL_Query = "INSERT INTO EMPLOYEE VALUES(?,?,?,?) ";
        jdbcTemplateObject.update(SQL_Query,id,firstName,lastName,address);
        System.out.println("Created " + id + " " + firstName + " " + lastName + " " + address);
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

    //Lay ra tat ca
    private List<Employee> getAll(){
        return jdbcTemplateObject.query("SELECT * FROM EMPLOYEE", new EmployeeMapper());
    }

    //Search 1 employee
    @Override
    public Employee getEmployee(Integer id){
        String SQL_Query = "SELECT * FROM EMPLOYEE WHERE ID = ?";
        Employee employee = jdbcTemplateObject.queryForObject(
                SQL_Query, new Object[] {id}, new EmployeeMapper());

        return employee;
    }

    @Override
    public void print(){

        List<Employee> employees = this.getAll();
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
