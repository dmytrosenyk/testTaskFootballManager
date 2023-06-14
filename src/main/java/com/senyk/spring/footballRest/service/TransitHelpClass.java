package com.senyk.spring.footballRest.service;

import java.math.BigDecimal;
import java.math.MathContext;

public class TransitHelpClass {
    public static BigDecimal transit(Long experience, Long age){
        BigDecimal experiences = new BigDecimal(experience);
        BigDecimal ages = new BigDecimal(age);
        BigDecimal b3 =experiences.multiply(BigDecimal.valueOf(100000.0));
        b3=b3.divide(ages, MathContext.DECIMAL128);
        return b3;
    }

    public static BigDecimal commission(BigDecimal transit,BigDecimal commission){
        BigDecimal b1=transit.multiply(commission);

        b1=b1.divide(BigDecimal.valueOf(100.0), MathContext.DECIMAL128);
        return b1;

    }

    public static BigDecimal sum(BigDecimal transit,BigDecimal commission){
        return transit.add(commission);
    }
}
