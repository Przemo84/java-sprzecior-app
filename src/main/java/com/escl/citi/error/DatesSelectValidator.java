package com.escl.citi.error;

import org.springframework.stereotype.Component;

@Component
public class DatesSelectValidator {


    public Boolean validate(String startDate, String endDate) {

        if (!startDate.equals("") && endDate.equals(""))
            return false;

        return !startDate.equals("") || endDate.equals("");
    }
}

