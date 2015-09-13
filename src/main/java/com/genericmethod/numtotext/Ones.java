package com.genericmethod.numtotext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cfarrugia
 */
public enum Ones {

    ZERO (0,"zero"),
    ONE (1,"one"),
    TWO (2,"two"),
    THREE (3,"three"),
    FOUR (4,"four"),
    FIVE (5,"five"),
    SIX (6,"six"),
    SEVEN (7,"seven"),
    EIGHT (8,"eight"),
    NINE (9,"nine"),
    TEN(10,"ten") ,
    ELEVEN(11,"eleven") ,
    TWELVE(12,"twelve") ,
    THIRTEEN(13,"thirteen") ,
    FOURTEEN(14,"fourteen") ,
    FIFTEEN(15,"fifteen") ,
    SIXTEEN(16,"sixteen") ,
    SEVENTEEN(17,"seventeen") ,
    EIGHTEEN(18,"eighteen") ,
    NINTEEN(19,"nineteen");

    private final int number;
    private final String description;
    private static Map<Integer, String> lookup = new HashMap<>();

     Ones(int number, String description) {
       this.number = number;
       this.description = description;
    }

    static {
        for (Ones number : Ones.values()) {
            lookup.put(number.getNumber(), number.getDescription());
        }
    }

    public static String getFromNumber(int number){
       return lookup.get(number);
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }
}
