package com.senyk.spring.footballRest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Team {
    @Id
    @SequenceGenerator(
            name = "team_sequence",
            sequenceName = "team_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "team_sequence"
    )
    private Long id;
    private String name;
    private LocalDate dateOfCreate;
    private BigDecimal budget;
    private BigDecimal commission;
    @OneToMany(mappedBy = "team")
    private List<Player> players;

}
