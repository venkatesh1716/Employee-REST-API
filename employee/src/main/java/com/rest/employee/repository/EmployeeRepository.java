package com.rest.employee.repository;

import com.rest.employee.Exceptions.UserNotFoundException;
import com.rest.employee.entity.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@Component
public class EmployeeRepository implements ServletContextAware {


    public ServletContext servletContext;
    public List<Employee> empList= new ArrayList<>();
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Employee> findAll() {
        empList = jdbcTemplate.query("select * from employee", ((resultSet, rownumber) -> new Employee(
                resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                resultSet.getString(4), resultSet.getLong(5))));
        return empList;
    }
    public Employee findOne(int id)
    {
        Employee employee=null;
        String query="select *from employee where emp_id=?";
        try {
            employee = jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(Employee.class));

        } catch (DataAccessException e) {

        }
        return employee;
    }

    public void savefile(InputStream inputStream, String path,String id){
        try{
            OutputStream outputStream= new FileOutputStream(new File(path));
            int read;
            byte[] bytes= new byte[1277712];
            while ((read=inputStream.read(bytes))!= -1){
                outputStream.write(bytes,0,read);
            }
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String savefiles(MultipartFile file) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
            String newFileName = simpleDateFormat.format(new Date()) + file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            Path path = Paths.get(this.servletContext.getRealPath("uploads/images/"+ newFileName));
            Files.write(path,bytes);
            return  newFileName;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Employee> createEmploy(Employee employee)
    {
        String query="insert into employee(firstname,lastname,email,phone) values(?,?,?,?)";
         jdbcTemplate.update(query,new Object[]{
                String.valueOf(employee.getFirstname()),employee.getLastname(),
                employee.getFirstname(),employee.getPhone()});
         return findAll();

    }
    public String deleteEmployee(int id)
    {
        String query="delete from employee where Emp_id=?";
        int st=jdbcTemplate.update(query,id);
        if(st==0)
        {
            throw new UserNotFoundException("No records are available with  id" +" "+ id);
        }
       return "deleted";
    }
    public Employee updateEmployee(Employee employee)
    {
        String query="update employee set firstname=?,lastname=?,email=?,phone=? where emp_id=?";
        jdbcTemplate.update(query,new Object[]{
                employee.getFirstname(),employee.getLastname(),employee.getEmail(),employee.getPhone(),
                Integer.valueOf(employee.getEmp_id())
        });
        return employee;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext=servletContext;
    }
}



