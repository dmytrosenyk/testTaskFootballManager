package com.senyk.spring.footballRest.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TeamDTO {
    private String name;
    private LocalDate dateOfCreate;
    private BigDecimal budget;
    private BigDecimal commission;
}
