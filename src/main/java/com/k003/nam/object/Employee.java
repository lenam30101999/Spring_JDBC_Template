package com.k003.nam.object;

public class Employee {

    private int id_Employee;
    private String firstName_Employee;
    private String lastName_Employee;
    private String address_Employee;

    public Employee(){

    }

    public Employee(int id_Employee, String firstName_Employee
            , String lastName_Employee, String address_Employee) {
        this.id_Employee = id_Employee;
        this.firstName_Employee = firstName_Employee;
        this.lastName_Employee = lastName_Employee;
        this.address_Employee = address_Employee;
    }

    public int getId_Employee() {
        return id_Employee;
    }

    public void setId_Employee(int id_Employee) {
        this.id_Employee = id_Employee;
    }

    public String getFirstName_Employee() {
        return firstName_Employee;
    }

    public void setFirstName_Employee(String firstName_Employee) {
        this.firstName_Employee = firstName_Employee;
    }

    public String getLastName_Employee() {
        return lastName_Employee;
    }

    public void setLastName_Employee(String lastName_Employee) {
        this.lastName_Employee = lastName_Employee;
    }

    public String getAddress_Employee() {
        return address_Employee;
    }

    public void setAddress_Employee(String address_Employee) {
        this.address_Employee = address_Employee;
    }

}
