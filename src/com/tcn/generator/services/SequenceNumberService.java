package com.tcn.generator.services;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SequenceNumberService {

    public int getSequenceNumber(String fileName){
        int sequenceNumber = 0;
        try {
            String data = sequenceNumberReader(fileName);
            if(!data.isEmpty() && data != null){
                sequenceNumber = Integer.parseInt(data);
            }
        }catch (Exception ex){
            ex.printStackTrace();;
        }
        System.out.println("current sequence number = "+ sequenceNumber);
        return sequenceNumber;
    }

    public void updateSequenceNumber(String fileName, int sequenceNumber){
        ++sequenceNumber;
        String newSequenceNumber = String.valueOf(sequenceNumber);
        try {
            Writer fileWriter = new FileWriter(fileName, false);
            fileWriter.write(newSequenceNumber);
            fileWriter.flush();
            fileWriter.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println("updated sequence number = "+sequenceNumber);
    }

    public String sequenceNumberReader(String fileName){
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        }catch (Exception ex){
            ex.printStackTrace();
            return CustomCheckedException.MISSING_SEQUENCE_FILE;
        }
        return data;
    }

}
