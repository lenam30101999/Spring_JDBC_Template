package com.k003.nam.UserController;

import com.k003.nam.dataSource.EmployeeJDBCTemplate;
import com.k003.nam.object.Employee;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class UserController {

    private Scanner scanner = new Scanner(System.in);

    private ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

    private EmployeeJDBCTemplate employeeJDBCTemplate = (EmployeeJDBCTemplate)

            context.getBean("employeeJDBCTemplate");

    private int choice = 0;
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private Employee employee;
    private List<Employee> lists;

    private void init(){
        id = "";
        firstName = "";
        lastName = "";
        address = "";
        employee = new Employee();
        lists = new ArrayList<>();
        EmployeeJDBCTemplate jdbcTemplate = new EmployeeJDBCTemplate();
    }

    private Employee creation(){
        init();
        System.out.println("Enter ID : ");
        scanner.nextLine();
        id = scanner.nextLine();

        System.out.println("First name: ");
        firstName = scanner.nextLine();

        System.out.println("Last name: ");
        lastName = scanner.nextLine();

        System.out.println("Address:");
        address = scanner.nextLine();

        employee.setId_Employee(Integer.parseInt(id));
        employee.setFirstName_Employee(firstName);
        employee.setLastName_Employee(lastName);
        employee.setAddress_Employee(address);

        return employee;
    }

    public void controller(){
        int totalInput = 0;
        init();

        do {
            System.out.println("---EMPLOYEE---");
            System.out.println("  1.Create");
            System.out.println("  2.Update");
            System.out.println("  3.Delete");
            System.out.println("  4.Search");
            System.out.println("  5.Show all");
            System.out.println("  0.Exit");
            System.out.println("Choice: ");
            choice = scanner.nextInt();

            switch (choice){
                case 1:{
                    System.out.println("----Creation----");
                    employeeJDBCTemplate.create(creation());
                    break;
                }

                case 2:{
                    System.out.println("----Update----");
                    System.out.println("Enter ID : ");
                    scanner.nextLine();
                    id = scanner.nextLine();
                    System.out.println("Address:");
                    address = scanner.nextLine();

                    employeeJDBCTemplate.update(Integer.parseInt(id), address);
                    break;
                }

                case 3:{
                    System.out.println("----Delete----");
                    System.out.println("ID: ");
                    scanner.nextLine();
                    id = scanner.nextLine();
                    employeeJDBCTemplate.delete(Integer.parseInt(id));
                    break;
                }

                case 4:{
                    System.out.println("ID: ");
                    scanner.nextLine();
                    id = scanner.nextLine();
                    employee = employeeJDBCTemplate.getEmployee(Integer.parseInt(id));

                    System.out.println("\n----Employee----");
                    System.out.printf("%-20d%-30s%-30s%-30s\n",
                            employee.getId_Employee(),
                            employee.getFirstName_Employee(),
                            employee.getLastName_Employee(),
                            employee.getAddress_Employee());
                    break;
                }

                case 5:{
                    employeeJDBCTemplate.print();
                    break;
                }

                default:
                    System.out.println("Enter again!");
            }
        }while (choice != 0);

    }
}
