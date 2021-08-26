package com.siemens.employee.service;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UtilityService {


    public String createRandomPassword(){
        int n =8;
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();

    }

    public String epochToDateTime(long epoch){
        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

        // Creating date from milliseconds
        // using Date() constructor
        Date result = new Date(epoch);

        // Formatting Date according to the
        // given format
        return  simple.format(result);
    }

}
