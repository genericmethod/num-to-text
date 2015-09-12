package com.genericmethod.numtotext;

import com.google.common.base.Splitter;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author cfarrugia
 */
public class Converter {



    public String convert(Integer number){

        StringBuilder text = new StringBuilder();

        //split the number into hundreds
        String numericString = StringUtils.reverse(number.toString());
        final Collection<String> hundredChunks = (Collection<String>) splitInHundreds(numericString);
        int numberOfChuncks = hundredChunks.size();
        ArrayList<String> textList = new ArrayList<>();
        int count = 1;
        for (String token : hundredChunks) {
            textList.add(token);
            if (count != numberOfChuncks - 1) text.append(Scale.getFromNumber(count));
            count++;
        }

        return text.toString();

    }

    public String convertHundreds(String token) {
        StringBuilder text = new StringBuilder();
        boolean firstNumberIsZero = false;
        boolean secondNumberIsZero = false;
        boolean thirdNumberIsZero = false;

        if (Character.getNumericValue(StringUtils.reverse(token).charAt(0)) == 0 && token.length() == 1){
            return Ones.ZERO.getDescription();
        }

        if (token.length() > 1 && Character.getNumericValue(StringUtils.reverse(token).charAt(0)) == 0){
            firstNumberIsZero = true;
        }

        if (token.length() > 1 && Character.getNumericValue(StringUtils.reverse(token).charAt(1)) == 0){
            secondNumberIsZero = true;
        }

        if (token.length() > 2 && Character.getNumericValue(StringUtils.reverse(token).charAt(2)) == 0){
            thirdNumberIsZero = true;
        }


        if (token.length() == 1){
             text.append(Ones.getFromNumber(new Integer(token)));
        }

        if(token.length() == 2) {
             text.append(Tens.getFromNumber(Character.getNumericValue(token.charAt(0)))).append(StringUtils.SPACE)
                     .append(((firstNumberIsZero) ? StringUtils.EMPTY : Ones.getFromNumber(Character.getNumericValue(token.charAt(1)))));
        }

        if(token.length() == 3) {
             text.append(thirdNumberIsZero ? StringUtils.EMPTY : Ones.getFromNumber(Character.getNumericValue(token.charAt(0))))
                     .append(StringUtils.SPACE)
                     .append(thirdNumberIsZero ? StringUtils.EMPTY : Scale.HUNDRED.getDescription())
                     .append(StringUtils.SPACE)
                     .append(secondNumberIsZero ? StringUtils.EMPTY : Tens.getFromNumber(Character.getNumericValue(token.charAt(1))))
                     .append(StringUtils.SPACE)
                     .append(firstNumberIsZero ? StringUtils.EMPTY : Ones.getFromNumber(Character.getNumericValue(token.charAt(2))));
        }

        return StringUtils.trim(text.toString());
    }


    public Iterable<String> splitInHundreds(String numericString){
        return Splitter.fixedLength(3).split(numericString);
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
