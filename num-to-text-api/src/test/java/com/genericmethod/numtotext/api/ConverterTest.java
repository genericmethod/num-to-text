package com.genericmethod.numtotext.api;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * @author cfarrugia
 */
public class ConverterTest {


    @Test
    public void testConvertHundreds(){
        assertEquals("one hundred eleven", Number.handleChunk("111"));
        assertEquals("zero", Number.handleChunk("0"));
        assertEquals("ten", Number.handleChunk("10"));
        assertEquals("eleven", Number.handleChunk("11"));
        assertEquals("twenty five", Number.handleChunk("25"));
        assertEquals("one hundred", Number.handleChunk("100"));
        assertEquals("one hundred twenty five", Number.handleChunk("125"));
        assertEquals("ten", Number.handleChunk("010"));
        assertEquals("eleven", Number.handleChunk("011"));
        assertEquals("two hundred fifty", Number.handleChunk("250"));
        assertEquals(StringUtils.EMPTY, Number.handleChunk("000"));
        assertEquals("twenty", Number.handleChunk("020"));
        assertEquals("twenty five", Number.handleChunk("025"));
    }

    @Test
    public void testGetTextListFromChunks(){

        ArrayList<String> onethousand = new ArrayList<>();
        onethousand.add("000");
        onethousand.add("1");

        final ArrayList<String> textListFromChunks = Number.getTextListFromChunks(onethousand);
        assertEquals(StringUtils.EMPTY, textListFromChunks.get(0));
        assertEquals(Scale.THOUSAND.getDescription(), textListFromChunks.get(1));
        assertEquals(Ones.ONE.getDescription(), textListFromChunks.get(2));

    }


    @Test
    public void testConvert() {

        assertEquals("one million eight hundred eighty eight thousand seven hundred twenty six", new Number(BigInteger.valueOf(1888726)).words());
        assertEquals("one hundred one", new Number(BigInteger.valueOf(101)).words());
        assertEquals("one thousand", new Number(BigInteger.valueOf(1000)).words());
        assertEquals("one thousand one", new Number(BigInteger.valueOf(1001)).words());
        assertEquals("one thousand twenty", new Number(BigInteger.valueOf(1020)).words());
        assertEquals("one thousand one hundred", new Number(BigInteger.valueOf(1100)).words());
        assertEquals("one thousand one hundred fifty", new Number(BigInteger.valueOf(1150)).words());
        assertEquals("one hundred twenty one thousand one hundred fifty", new Number(BigInteger.valueOf(121150)).words());
        assertEquals("one billion", new Number(BigInteger.valueOf(1000000000)).words());
        assertEquals("one million", new Number(BigInteger.valueOf(1000000)).words());
        assertEquals("one trillion", new Number(BigInteger.valueOf(10).pow(12)).words());

    }

    @Test
    public void testLargeNumbers() throws InterruptedException {

        Long count = 1000000l;
        BigInteger number = BigInteger.valueOf(10).pow(51).add(BigInteger.valueOf(1888726)).add(BigInteger.valueOf(2).pow(47));
        long startTime = System.nanoTime();
        while (count > 0) {
            //System.out.println(count);
            count--;
            new Number(number).words();
        }

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println(TimeUnit.SECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS));
    }

}
