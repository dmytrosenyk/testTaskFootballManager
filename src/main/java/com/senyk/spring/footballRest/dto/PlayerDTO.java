package com.senyk.spring.footballRest.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PlayerDTO {
    private String name;
    private LocalDate dateOfBirth;
    private LocalDate dateOfStartCareer;
    private String team;
}
