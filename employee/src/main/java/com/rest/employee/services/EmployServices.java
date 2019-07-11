/*
package com.rest.employee.services;

import com.rest.employee.Exceptions.InsertionFailed;
import com.rest.employee.Exceptions.UserNotFoundException;
import com.rest.employee.entity.DBO;
import com.rest.employee.entity.Employee;
import com.rest.employee.entity.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


@Component
public class EmployServices {
  */
/*  Log mylog;

    public void log(){
        try{
            mylog=new Log("log.txt");
            mylog.logger.setLevel(Level.INFO);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
*//*

  public  static Connection con = DBO.getCon();


    public List<Employee> findAll() throws SQLException {
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        String query = "select *from employee";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getLong(5));
            employeeArrayList.add(employee);
        }
        return employeeArrayList;
    }

    public Employee findOne(int id){
       */
/* log();
        mylog.logger.info("Finding employee by Id");*//*

        Employee emp=null;
        try{
            PreparedStatement ps = con.prepareStatement("select * from employee where emp_id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs!=null) {
                while (rs.next()) {
                    emp = new Employee(rs.getInt(1), rs.getString(2),
                            rs.getString(3), rs.getString(4), rs.getLong(5));
                }
            }
        }catch(Exception ep){
            ep.printStackTrace();
        }
        return emp;
    }

    public String deleteEmployee(@PathVariable int id) {
       // log();
       // mylog.logger.info("Deleting employee by id");

        try {
            PreparedStatement ps = con.prepareStatement("delete from employee where Emp_id=?");
            ps.setInt(1, id);
            int count=ps.executeUpdate();
           //  mylog.logger.info("Deleted successfully");
            if (count==0) throw new UserNotFoundException("failed");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Employee updateEmployee(Employee employee) {
        int count = 0;
        try {
            PreparedStatement ps = con.prepareStatement("update employee set firstname =?,lastname=?,email=?,phone=? where emp_id=?");
            ps.setString(1, employee.getFirstname());
            ps.setString(2, employee.getLastname());
            ps.setString(3, employee.getEmail());
            ps.setLong(4, employee.getPhone());
            ps.setInt(5, employee.getEmp_id());
            count = ps.executeUpdate();
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  employee;
    }

    public String createEmployee(Employee employee) {
       */
/* log();
        mylog.logger.info("Adding Employee");*//*

        int count = 0;
        try {
            PreparedStatement ps = con.prepareStatement("insert into employee(firstname,lastname,email,phone) values(?,?,?,?)");
            ps.setString(1, employee.getFirstname());
            ps.setString(2, employee.getLastname());
            ps.setString(3, employee.getEmail());
            ps.setLong(4, employee.getPhone());
            count = ps.executeUpdate();
            if (count == 1) {
              */
/*  log();
                mylog.logger.info("Record inserted successfully with name" + " " + employee.getFirstname() + " " + employee.getLastname());*//*

                return "Record inserted successfully with name" + " " + employee.getFirstname() + " " + employee.getLastname();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count == 0) {
           */
/* log();
            mylog.logger.info("Insertion failed!!!");*//*

            throw new InsertionFailed("Insertion failed, all fields are unique");
        }
        return null;
    }

}
*/
