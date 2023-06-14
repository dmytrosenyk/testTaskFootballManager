package com.senyk.spring.footballRest.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Player {
    @Id
    @SequenceGenerator(
            name = "player_sequence",
            sequenceName = "player_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequence"
    )
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private LocalDate dateOfStartCareer;
    @Transient
    private Long age;
    @Transient
    private Long experience;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Long getAge() {
        return ChronoUnit.YEARS.between(dateOfBirth,LocalDate.now());
    }

    public Long getExperience() {
        return ChronoUnit.MONTHS.between(dateOfStartCareer,LocalDate.now());
    }
}
