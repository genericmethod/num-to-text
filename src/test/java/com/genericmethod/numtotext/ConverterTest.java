package com.genericmethod.numtotext;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author cfarrugia
 */
public class ConverterTest {

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
        assertEquals("twenty five", converter.convertHundreds("25"));
        assertEquals("one hundred", converter.convertHundreds("100"));
        assertEquals("one hundred twenty five", converter.convertHundreds("125"));

    }
}
