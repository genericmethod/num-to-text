package com.genericmethod.numtotext.api;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author cfarrugia
 */
public class Number {

    public BigInteger number;

    public Number(BigInteger number){
        this.number = number;
    }

    public Number(int number){
        this.number = BigInteger.valueOf(number);
    }

    public Number(long number){
        this.number = BigInteger.valueOf(number);
    }

    public String words(){
       return Number.convert(this.number);
    }

    public static String convert(BigInteger number) {
        String numericString = StringUtils.reverse(number.toString());
        final ArrayList<String> hundredChunks = Lists.newArrayList(splitInHundreds(numericString));
        ArrayList<String> textList = getTextListFromChunks(hundredChunks);
        Collections.reverse(textList);
        textList.removeAll(Collections.singleton(StringUtils.EMPTY));
        return StringUtils.trim(StringUtils.join(textList, StringUtils.SPACE));
    }

    public static ArrayList<String> getTextListFromChunks(ArrayList<String> hundredChunks) {
        int numberOfChunks = hundredChunks.size();
        ArrayList<String> textList = new ArrayList<>();
        int count = 0;
        for (String token : hundredChunks) {
            textList.add(handleChunk(StringUtils.reverse(token)));
            if (count < numberOfChunks - 1 && !hundredChunks.get(count + 1).equals("000")) {
                textList.add(Scale.getFromNumber(count + 2));
            }
            count++;
        }
        return textList;
    }

    public static String handleChunk(String token) {
        StringBuilder words = new StringBuilder();

        if (Character.getNumericValue(StringUtils.reverse(token).charAt(0)) == 0 && token.length() == 1) {
            return Ones.ZERO.getDescription();
        }

        if (token.equals("000")) return StringUtils.EMPTY;

        boolean firstNumberIsZero = isFirstNumberZero(token);
        boolean secondNumberIsZero = isSecondNumberZero(token);
        boolean thirdNumberIsZero = isThirdNumberZero(token);

        if (token.length() == 1) {
            return handleSingleDigit(token);
        }

        if (token.length() == 2) {
            return handleDoubleDigit(token, firstNumberIsZero);
        }

        if (token.length() == 3) {
            return handleTripleDigit(token, firstNumberIsZero, secondNumberIsZero, thirdNumberIsZero);
        }

        return token;
    }

    private static String handleTripleDigit(String token, boolean firstNumberIsZero, boolean secondNumberIsZero, boolean thirdNumberIsZero) {

        String doubleDigitWords =  handleDoubleDigit(token.substring(1), firstNumberIsZero);

        if (!thirdNumberIsZero) {
            return StringUtils.trim(Ones.getFromNumber(Character.getNumericValue(token.charAt(0))) +
                    " " +
                    Scale.HUNDRED.getDescription()) +
                    (!doubleDigitWords.equals(StringUtils.EMPTY) ? " " : "")
                    + doubleDigitWords;
        }

        return StringUtils.trim(doubleDigitWords);
    }

    private static String handleSingleDigit(String token) {
        return Ones.getFromNumber(new Integer(token));
    }

    public static String handleDoubleDigit(String token, boolean firstNumberIsZero) {

        String words = StringUtils.EMPTY;

        if (token.equals("00")) {return StringUtils.EMPTY;}

        if (Integer.parseInt(token) < 20) {
            words = Ones.getFromNumber(Integer.parseInt(token));
        } else {
            words = Tens.getFromNumber(Character.getNumericValue(token.charAt(0)))+
                    (firstNumberIsZero ? "" : " ") +
                    (((firstNumberIsZero) ? StringUtils.EMPTY : Ones.getFromNumber(Character.getNumericValue(token.charAt(1)))));
        }

        return words;
    }

    private static boolean isThirdNumberZero(String token) {
        boolean thirdNumberIsZero = false;
        if (token.length() > 2 && Character.getNumericValue(StringUtils.reverse(token).charAt(2)) == 0) {
            thirdNumberIsZero = true;
        }
        return thirdNumberIsZero;
    }

    private static boolean isSecondNumberZero(String token) {
        boolean secondNumberIsZero = false;
        if (token.length() > 1 && Character.getNumericValue(StringUtils.reverse(token).charAt(1)) == 0) {
            secondNumberIsZero = true;
        }
        return secondNumberIsZero;
    }

    public static boolean isFirstNumberZero(String token) {
        boolean firstNumberIsZero = false;
        if (token.length() > 0 && Character.getNumericValue(StringUtils.reverse(token).charAt(0)) == 0) {
            firstNumberIsZero = true;
        }
        return firstNumberIsZero;
    }

    public static Iterable<String> splitInHundreds(String numericString) {
        return Splitter.fixedLength(3).split(numericString);
    }
}