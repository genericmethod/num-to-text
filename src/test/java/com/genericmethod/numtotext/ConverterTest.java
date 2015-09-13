package com.genericmethod.numtotext;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author cfarrugia
 */
public class ConverterTest {


    Converter converter = new Converter();

    @Test
    public void testSplitToHundred() {

        Converter converter = new Converter();
        assertEquals(1, converter.splitToHundreds("1").size());
        assertEquals(1, converter.splitToHundreds("12").size());
        assertEquals(1, converter.splitToHundreds("123").size());
        assertEquals(2, converter.splitToHundreds("1234").size());
        assertEquals(2, converter.splitToHundreds("12345").size());
        assertEquals(2, converter.splitToHundreds("123456").size());
        assertEquals(3, converter.splitToHundreds("1234567").size());

        assertEquals("1", converter.splitToHundreds("1").get(0));
        assertEquals("12", converter.splitToHundreds("12").get(0));
        assertEquals("123", converter.splitToHundreds("123").get(0));

        assertEquals("234", converter.splitToHundreds("1234").get(0));
        assertEquals("1", converter.splitToHundreds("1234").get(1));

        assertEquals("345", converter.splitToHundreds("12345").get(0));
        assertEquals("12", converter.splitToHundreds("12345").get(1));


        assertEquals("456", converter.splitToHundreds("123456").get(0));
        assertEquals("123", converter.splitToHundreds("123456").get(1));

        assertEquals("567", converter.splitToHundreds("1234567").get(0));
        assertEquals("234", converter.splitToHundreds("1234567").get(1));
        assertEquals("1", converter.splitToHundreds("1234567").get(2));
    }


    @Test
    public void testConvertHundreds(){

        Converter converter = new Converter();
        assertEquals("zero", converter.convertHundreds("0"));
        assertEquals("ten", converter.convertHundreds("10"));
        assertEquals("eleven", converter.convertHundreds("11"));

        assertEquals("twenty five", converter.convertHundreds("25"));
        assertEquals("one hundred", converter.convertHundreds("100"));
        assertEquals("one hundred twenty five", converter.convertHundreds("125"));
        assertEquals("ten", converter.convertHundreds("010"));
        assertEquals("eleven", converter.convertHundreds("011"));
        assertEquals("two hundred fifty", converter.convertHundreds("250"));
        assertEquals(StringUtils.EMPTY, converter.convertHundreds("000"));
        assertEquals("twenty", converter.convertHundreds("020"));
        assertEquals("twenty five", converter.convertHundreds("025"));

    }

    @Test
    public void testGetTextListFromChunks(){

        ArrayList<String> onethousand = new ArrayList<>();
        onethousand.add("000");
        onethousand.add("1");

        final ArrayList<String> textListFromChunks = converter.getTextListFromChunks(onethousand);
        assertEquals(StringUtils.EMPTY, textListFromChunks.get(0));
        assertEquals(Scale.THOUSAND.getDescription(), textListFromChunks.get(1));
        assertEquals(Ones.ONE.getDescription(), textListFromChunks.get(2));

    }


    @Test
    public void testConvert() {

        Converter converter = new Converter();
        assertEquals("one million eight hundred eighty eight thousand seven hundred twenty six", converter.convert(BigInteger.valueOf(1888726)));
        assertEquals("one hundred one", converter.convert(BigInteger.valueOf(101)));
        assertEquals("one thousand", converter.convert(BigInteger.valueOf(1000)));
        assertEquals("one thousand one", converter.convert(BigInteger.valueOf(1001)));
        assertEquals("one thousand twenty", converter.convert(BigInteger.valueOf(1020)));
        assertEquals("one thousand one hundred", converter.convert(BigInteger.valueOf(1100)));
        assertEquals("one thousand one hundred fifty", converter.convert(BigInteger.valueOf(1150)));
        assertEquals("one hundred twenty one thousand one hundred fifty", converter.convert(BigInteger.valueOf(121150)));
        assertEquals("one billion", converter.convert(BigInteger.valueOf(1000000000)));
        assertEquals("one million", converter.convert(BigInteger.valueOf(1000000)));
        assertEquals("one trillion", converter.convert(BigInteger.valueOf(10).pow(12)));

    }


    public void testLargeNumbers() throws InterruptedException {
        BigInteger number = BigInteger.valueOf(10).pow(51).add(BigInteger.valueOf(1888726)).add(BigInteger.valueOf(10).pow(48));
        System.out.print(number+" -> " + converter.convert(number)+"\r\n");

    }

}
