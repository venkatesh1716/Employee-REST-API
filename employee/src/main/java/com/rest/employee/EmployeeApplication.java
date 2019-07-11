package com.rest.employee;

import com.rest.employee.entity.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;
import java.util.logging.LogManager;

@SpringBootApplication
public class EmployeeApplication {
    public static void main(String[] args) {
        Log mylog;
        SpringApplication.run(EmployeeApplication.class, args);
       /* try{
            LogManager.getLogManager().reset();
            mylog=new Log("log.txt");
            mylog.logger.setLevel(Level.INFO);
            mylog.logger.info("Employee Application Started");
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }

    }
