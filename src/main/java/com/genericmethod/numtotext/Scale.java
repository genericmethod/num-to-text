package com.genericmethod.numtotext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cfarrugia
 */
public enum Scale {

    HUNDRED (1,"hundred"),
    THOUSAND (2,"thousand"),
    MILLION (3,"million"),
    BILLION (4,"billion"),
    TRILLION (5,"trillion"),
    QUADRILLION (6,"quadrillion"),
    QUINTILLION (7,"quintillion"),
    SEXTILLION (8,"sextillion"),
    SEPTILLION (9,"septillion");

    private final int number;
    private final String description;
    private static Map<Integer, String> lookup = new HashMap<>();

    Scale(int number, String description) {
       this.number = number;
       this.description = description;
    }

    static {
        for (Scale number : Scale.values()) {
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
