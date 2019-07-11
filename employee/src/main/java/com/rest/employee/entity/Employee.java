package com.rest.employee.entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Employee {

    private int emp_id;

    @NotNull
    @Size(min  = 2,message = "first name should have atleast 2 characters")
    private String firstname;

    @NotNull
    @Size(min = 2)
    private String lastname;


    @NotNull
    @Size(min = 1, max = 20)
    @Pattern(regexp="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z-]+" +
            "(\\.A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    @Email
    private String email;

    @NotNull
    //@Size(max=10)
    private long phone;

    public int getEmp_id() {

        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "emp_id=" + emp_id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }

    public Employee(int emp_id, String firstname, String lastname, String email, long phone) {
        this.emp_id = emp_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    public Employee() {
    }


    public boolean equals(Object obj) {
        Employee e=(Employee)obj;
        return this.getFirstname().equals(e.getFirstname())&&
                this.getEmp_id()==e.getEmp_id() ;
    }

}
