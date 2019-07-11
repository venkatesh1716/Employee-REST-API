package com.rest.employee.entity;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class Log {
    public Logger logger;
    FileHandler fh;

    public Log(String file_name) throws IOException,SecurityException {
        File f = new File(file_name);
        if (!f.exists()){
            f.createNewFile();
        }
        fh=new FileHandler(file_name,true);
        logger=Logger.getLogger("Employee Application");
        logger.addHandler(fh);
        SimpleFormatter formatter= new SimpleFormatter();
        fh.setFormatter(formatter);

    }

    public static void main(String[] args) {
        try{
            Log mylog= new Log("log.txt");
            mylog.logger.setLevel(Level.INFO);
            //
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
