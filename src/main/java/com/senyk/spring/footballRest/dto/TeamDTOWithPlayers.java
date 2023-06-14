package com.senyk.spring.footballRest.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TeamDTOWithPlayers {
    private TeamDTO teamDTO;
    private List<String> players;

}
