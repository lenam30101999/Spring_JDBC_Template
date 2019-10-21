package com.k003.nam.UserController;

import com.k003.nam.dataSource.EmployeeJDBCTemplate;
import com.k003.nam.object.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private static final String template = "%s";

    private List<Employee> employeeList = new EmployeeJDBCTemplate().listEmployee();

    private String show(){
        String listString = null;

        for (Employee emp : employeeList){
            listString += listString + emp.getId_Employee() + "\t" +
                    emp.getFirstName_Employee() + "\t" +
                    emp.getLastName_Employee() + "\t" +
                    emp.getAddress_Employee() + "\n";
        }
        return listString;
    }

    @RequestMapping("/employee")
    public String greeting(@RequestParam(value = "show", defaultValue = "3") String show) {
        return show;
    }


}
