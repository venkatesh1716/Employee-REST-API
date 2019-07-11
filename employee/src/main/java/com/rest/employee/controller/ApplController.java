package com.rest.employee.controller;
import com.rest.employee.Exceptions.UserNotFoundException;
import com.rest.employee.repository.EmployeeRepository;
import com.rest.employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.List;
@RestController
public class ApplController  implements ServletContextAware {

    public static String upload_dir="uploads";
  //  public List<Employee> empList = new ArrayList<>();
     public ServletContext servletContext;

    @Autowired
    public EmployeeRepository repo;

    @GetMapping("employees")
    public List<Employee> getallEmployees() {
        return repo.findAll();
    }

    @GetMapping("employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = repo.findOne(id);
        if (employee == null) {
            throw new UserNotFoundException("No records are available with id" + " "
                    + id);
        }
        return employee;
    }

    @PostMapping("employees")
        public List<Employee> addEmployee(@Valid @RequestBody  Employee employee) {
            return repo.createEmploy(employee);
        }
    //for uploading single files
    @RequestMapping(value="employees/upload/{id}",method = RequestMethod.POST)
    public String upload(@PathVariable String id ,@RequestParam("file") MultipartFile file, HttpServletRequest request){
        try{
            String fileName=file.getOriginalFilename();
            String path=request.getServletContext().getRealPath("")+upload_dir + File.separator + fileName;
            repo.savefile(file.getInputStream(),path,id);
            File oldfile= new File(path);
            //File renameFile= new File(path+id);
            File renameFile= new File(path+"\\id");
            if (oldfile.renameTo(renameFile)){
                return "renamed successfully"+renameFile;
            }
            return "File Inserted at path"+" "+path;
        }catch (Exception e){
            return e.getMessage();
        }
    }


    //for uploading multiple files
    @RequestMapping(value = "employees/uploads", method = RequestMethod.POST)
    public ResponseEntity<Object> upload(@RequestParam("files") MultipartFile[] files) {
        try {
            System.out.println( "files list");
            for (MultipartFile file:files){
                System.out.println("file name:"+" "+file.getOriginalFilename());
                System.out.println("file Size:"+" "+file.getSize());
                System.out.println("file type:"+" "+file.getContentType());
                repo.savefiles(file);
            }
            return new ResponseEntity<>("files uploaded successfully" ,HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("files upload failed",HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return repo.updateEmployee(employee);
    }

/*
    @PutMapping("/employees")
    public ResponseEntity<Boolean> updateEmployee(@RequestBody Employee employee) {
        // return repo.updateEmployee(employee);
        repo.updateEmployee(employee);
        HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.add("key1","value1");
        httpHeaders.add("key2","venkatesh");

        return new ResponseEntity<>(    httpHeaders, HttpStatus.OK);

    }*/

    @DeleteMapping("employees/{id}")
    public List<Employee> deleteEmployee(@PathVariable int id) {
        String st = repo.deleteEmployee(id);
        if (st == null) {
            throw new UserNotFoundException("No records are available with ss id" + " " + id);
        } else {
            // return "Record deleted with Id"+" "+id+"and"+"\n"+"Name"+e.getFirstname()+" "+e.getLastname();
            return repo.findAll();
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext=servletContext;

    }
}



