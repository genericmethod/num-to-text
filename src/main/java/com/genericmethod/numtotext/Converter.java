package com.genericmethod.numtotext;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author cfarrugia
 */
public class Converter {

    public void convert(Integer number){

        //split the number into hundreds
        String numericString = StringUtils.reverse(number.toString());
        final ArrayList hundreds = splitToHundreds(numericString);
    }

    public ArrayList splitToHundreds(String numericString){

        int i;
        int chunkSize = 3;
        int numberOfChunks = numericString.length() / chunkSize;
        if(numericString.length() % chunkSize != 0) numberOfChunks++;
        ArrayList<String> listOfHundreds = new ArrayList<String>();

        if (numericString.length() <= chunkSize) {
            listOfHundreds.add(numericString);
            return listOfHundreds;
        }

        for (i = 1; i <= numberOfChunks; i++) {
            int start = (i-1) * chunkSize;
            int end = (chunkSize * i);
            if(i == numberOfChunks) {
                listOfHundreds.add(StringUtils.reverse(StringUtils.reverse(numericString).substring(start, numericString.length())));
            } else {
                listOfHundreds.add(StringUtils.reverse(StringUtils.reverse(numericString).substring(start, end)));
            }
        }


        return listOfHundreds;
    }

}
