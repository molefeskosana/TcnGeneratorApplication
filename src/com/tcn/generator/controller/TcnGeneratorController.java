package com.tcn.generator.controller;

import com.tcn.generator.services.CustomCheckedException;
import com.tcn.generator.services.TcnGeneratorImpl;

import java.io.File;
import java.net.InetAddress;

public class TcnGeneratorController {

    /**
     * sample expected hostnames below:
     * TLK = GPTLK01DEVESB01
     * BCX = GPBCX01DEVESB01
     */

    static TcnGeneratorImpl fileService = new TcnGeneratorImpl();
    public static String tcnNumber;
    static String hostname;

    public static String fetchTCN(String enSource){
        try{
           hostname = InetAddress.getLocalHost().getHostName();
           //To test uncomment the below hostname and replace it with one of the above mentioned hostnames.
           //hostname= "GPBCX01PRDCDR01";
        }catch (Exception e){
            e.getStackTrace();
            return  CustomCheckedException.ERROR_EXTARCTING_HOSTNAME;
        }
        System.out.println("current hostname :"+hostname);
        final String fileName = "/gfsiib/abisqmgr/iib/tcn/generator/sequencenumber.txt";
        if(hostname.contains("bcx") || hostname.contains("BCX")) {
            File file = new File(fileName);
            if (file.exists()) {
                tcnNumber = fileService.getBcxTcnValue(enSource, fileName);
            } else {
                return CustomCheckedException.MISSING_SEQUENCE_FILE+fileName;
            }
        }else if(hostname.contains("tlk") || hostname.contains("TLK")) {
            File file = new File(fileName);
            if (file.exists()) {
                tcnNumber = fileService.getTlkTcnValue(enSource, fileName);
            } else {
                return CustomCheckedException.MISSING_SEQUENCE_FILE+fileName;
            }
        }else {
            return CustomCheckedException.INVALID_HOSTNAME+hostname;
        }
        return tcnNumber;
    }

}

