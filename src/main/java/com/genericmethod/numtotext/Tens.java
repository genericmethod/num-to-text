package com.genericmethod.numtotext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cfarrugia
 */
public enum Tens {

    TEN (1,"ten"),
    TWENTY (2,"twenty"),
    THIRTY (3,"thirty"),
    FORTY (4,"forty"),
    FIFTY (5,"fifty"),
    SIXTY (6,"sixty"),
    SEVENTY (7,"seventy"),
    EIGHTY (8,"eighty"),
    NINETY (9,"ninety");

    private final int number;
    private final String description;
    private static Map<Integer, String> lookup = new HashMap<>();

    Tens(int number, String description) {
       this.number = number;
       this.description = description;
    }

    static {
        for (Tens number : Tens.values()) {
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
