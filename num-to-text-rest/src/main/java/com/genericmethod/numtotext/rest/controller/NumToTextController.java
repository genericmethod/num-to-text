package com.genericmethod.numtotext.rest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

/**
 * @author cfarrugia
 */
@RestController
public class NumToTextController {

    @RequestMapping("/num2text/{number}")
    public String num2text(@PathVariable(value="number") String number){
        return new com.genericmethod.numtotext.api.Number(new BigInteger(number)).words();
    }
}
