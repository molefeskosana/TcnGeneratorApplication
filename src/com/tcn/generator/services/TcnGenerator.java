package com.tcn.generator.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcnGenerator {

    public String calcDHACheckDigit(String i_strYY, Long i_TxNo){

        String strCheck = "";
        Long iRemainder = 0L;

        String alphabet = "ZABCDEFGHJKLMNPQRTUVWXY";

        char[] letters = alphabet.toCharArray();

        if(i_strYY != null && !i_strYY.isEmpty()){

            Long iYY = Long.parseLong(i_strYY);
            Long iValue = iYY * 1000000000 + i_TxNo;
            iRemainder = iValue % 23;

            strCheck = String.valueOf(letters[Math.toIntExact(iRemainder)]);
        }
        System.out.println("string check : "+strCheck);
        return strCheck;

    }

    public String generateTCN(String enSource, int iSequenceNum, String getDateFromFile){

        String strTCN = "";
        String strTxNo;
        String strYY = getDateFromFile;
        System.out.println("tcn date string : "+strYY);
        strTCN = enSource + strYY;
        strTxNo = String.format("%07d", iSequenceNum);
        System.out.println("tcn sequence number : "+strTxNo);
        String strCheck = calcDHACheckDigit(strYY,Long.valueOf(iSequenceNum));

        strTCN += strTxNo + strCheck;
        System.out.println("tcn value :"+ strTCN);
        return strTCN;
    }

}
