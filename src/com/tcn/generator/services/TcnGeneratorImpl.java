package com.tcn.generator.services;


public class TcnGeneratorImpl {

    TcnGenerator tcnGenerator = new TcnGenerator();
    SequenceNumberService sequenceNumberGenerator = new SequenceNumberService();
    DateService dateService = new DateService();
    String getDateFromFile;
    String currentDate;
    int bcxSequenceNumber;
    int tlkSequenceNumber;

    public String getBcxTcnValue(String enSource, String refFilename){
        String tcn = null;
        getDateFromFile = dateService.getDateFromFile();
        currentDate = dateService.currentDate();
        if(!getDateFromFile.contains("ERROR")){
            if(currentDate.equalsIgnoreCase(getDateFromFile)){
                try {
                    bcxSequenceNumber = sequenceNumberGenerator.getSequenceNumber(refFilename);
                    tcn = tcnGenerator.generateTCN(enSource, bcxSequenceNumber,currentDate);
                    sequenceNumberGenerator.updateSequenceNumber(refFilename,bcxSequenceNumber);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }else{
                bcxSequenceNumber = 0;
                tcn = tcnGenerator.generateTCN(enSource, bcxSequenceNumber,getDateFromFile);
                sequenceNumberGenerator.updateSequenceNumber(refFilename,bcxSequenceNumber);
                dateService.updateDateoNaFile(currentDate);
            }
        }else{
            return getDateFromFile;
        }


        return tcn;
    }

    public String getTlkTcnValue(String enSource, String refFilename){
        String tcn = null;
        getDateFromFile = dateService.getDateFromFile();
        currentDate = dateService.currentDate();
        if(!getDateFromFile.contains("ERROR")){
            if(currentDate.equalsIgnoreCase(getDateFromFile)){
                tlkSequenceNumber = sequenceNumberGenerator.getSequenceNumber(refFilename);
                if(tlkSequenceNumber < 5000000){
                	tlkSequenceNumber = 5000000;
                }
                try {
                    tcn = tcnGenerator.generateTCN(enSource, tlkSequenceNumber,currentDate);
                    sequenceNumberGenerator.updateSequenceNumber(refFilename,tlkSequenceNumber);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }else{
                tlkSequenceNumber = 5000000;
                tcn = tcnGenerator.generateTCN(enSource, tlkSequenceNumber,getDateFromFile);
                sequenceNumberGenerator.updateSequenceNumber(refFilename,tlkSequenceNumber);
                dateService.updateDateoNaFile(currentDate);

            }
        }else {
            return getDateFromFile;
        }


        return tcn;
    }
}
