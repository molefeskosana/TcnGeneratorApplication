package com.tcn.generator.services;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateService {

    public String currentDate(){
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String currenDate = null;
        try{
        String today = formatter.format(date);
        date = formatter.parse(today);
        formatter = new SimpleDateFormat("yyMMdd");
        currenDate = formatter.format(date);
        System.out.println("current date = "+ formatter.format(date));
        }catch(Exception ex){
        	ex.printStackTrace();
        }
        
        return currenDate;
    }

    public void updateDateoNaFile(String currentDate){
        String date = currentDate;
        String dateFile = "/gfsiib/abisqmgr/iib/tcn/generator/datefile.txt";
        File file = new File(dateFile);
        if(file.exists()){
            try {
                Writer fileWriter = new FileWriter(dateFile, false);
                fileWriter.write(date);
                fileWriter.flush();
                fileWriter.close();
                System.out.println("updated date = "+date);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else{
            System.out.println(CustomCheckedException.MISSNG_DATE_FILE);
        }
    }

    public String getDateFromFile(){
        String data = "";
        String dateFile = "/gfsiib/abisqmgr/iib/tcn/generator/datefile.txt";
        File file = new File(dateFile);
        if(file.exists()){
            try {
                data = new String(Files.readAllBytes(Paths.get(dateFile)));
                if(data.isEmpty() || data.equals(null)){
                    updateDateoNaFile(currentDate());
                    return currentDate();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else {
            return CustomCheckedException.MISSNG_DATE_FILE+dateFile;
        }
        System.out.println("Date from a file = "+data);
        return data;
    }
}
