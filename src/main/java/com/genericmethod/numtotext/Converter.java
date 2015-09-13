package com.genericmethod.numtotext;

import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author cfarrugia
 */
public class Converter {

    public String convert(BigInteger number) {
        StringBuilder text = new StringBuilder();
        String numericString = StringUtils.reverse(number.toString());
        final ArrayList<String> hundredChunks = Lists.newArrayList(splitInHundreds(numericString));
        ArrayList<String> textList = getTextListFromChunks(hundredChunks);
        Collections.reverse(textList);
        textList.removeAll(Collections.singleton(""));
        return StringUtils.trim(StringUtils.join(textList, StringUtils.SPACE));
    }

    public ArrayList<String> getTextListFromChunks(ArrayList<String> hundredChunks) {
        int numberOfChunks = hundredChunks.size();
        ArrayList<String> textList = new ArrayList<>();
        int count = 0;
        for (String token : hundredChunks) {
            textList.add(convertHundreds(StringUtils.reverse(token)));
            if (count < hundredChunks.size() - 1 && !hundredChunks.get(count + 1).equals("000")) {
                textList.add(Scale.getFromNumber(count + 2));
            }
            count++;
        }
        return textList;
    }

    public String convertHundreds(String token) {
        StringBuilder text = new StringBuilder();
        boolean firstNumberIsZero = false;
        boolean secondNumberIsZero = false;
        boolean thirdNumberIsZero = false;

        if (Character.getNumericValue(StringUtils.reverse(token).charAt(0)) == 0 && token.length() == 1) {
            return Ones.ZERO.getDescription();
        }

        if (token.equals("000")) return StringUtils.EMPTY;

        if (token.length() > 1 && Character.getNumericValue(StringUtils.reverse(token).charAt(0)) == 0) {
            firstNumberIsZero = true;
        }

        if (token.length() > 1 && Character.getNumericValue(StringUtils.reverse(token).charAt(1)) == 0) {
            secondNumberIsZero = true;
        }

        if (token.length() > 2 && Character.getNumericValue(StringUtils.reverse(token).charAt(2)) == 0) {
            thirdNumberIsZero = true;
        }

        if (token.length() == 1) {
            return text.append(Ones.getFromNumber(new Integer(token))).toString();
        }

        if (token.length() == 2) {
            return handleDoubleDigit(token, firstNumberIsZero);
        }

        if (token.length() == 3) {
            if (thirdNumberIsZero) {
                return handleDoubleDigit(token.substring(1), firstNumberIsZero);
            }
            return StringUtils.trim(text.append(Ones.getFromNumber(Character.getNumericValue(token.charAt(0))))
                    .append(StringUtils.SPACE)
                    .append(Scale.HUNDRED.getDescription())
                    .append(secondNumberIsZero ? StringUtils.EMPTY : StringUtils.SPACE)
                    .append(secondNumberIsZero ? StringUtils.EMPTY : Tens.getFromNumber(Character.getNumericValue(token.charAt(1))))
                    .append(StringUtils.SPACE)
                    .append(firstNumberIsZero ? StringUtils.EMPTY : Ones.getFromNumber(Character.getNumericValue(token.charAt(2)))).toString());

        }

        return token;
    }

    public String handleDoubleDigit(String token, boolean firstNumberIsZero) {

        StringBuilder text = new StringBuilder();

        if (Integer.parseInt(token) < 20) {
            text.append(Ones.getFromNumber(Integer.parseInt(token))).append(StringUtils.SPACE);
        } else {
            text.append(Tens.getFromNumber(Character.getNumericValue(token.charAt(0)))).append(StringUtils.SPACE)
                    .append(((firstNumberIsZero) ? StringUtils.EMPTY : Ones.getFromNumber(Character.getNumericValue(token.charAt(1)))));
        }

        return StringUtils.trim(text.toString());
    }


    public Iterable<String> splitInHundreds(String numericString) {
        return Splitter.fixedLength(3).split(numericString);
    }
}