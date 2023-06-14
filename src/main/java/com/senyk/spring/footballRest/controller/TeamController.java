package com.senyk.spring.footballRest.controller;

import com.senyk.spring.footballRest.dto.*;
import com.senyk.spring.footballRest.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/team")
public class TeamController {
    private final TeamService teamService;
    private final Converter converter;

    @Autowired
    public TeamController(TeamService teamService, Converter converter) {
        this.teamService = teamService;
        this.converter = converter;
    }

    @PostMapping("/crud")
    public void addNewTeam(@RequestBody TeamDTO teamDTO){
        teamService.addNewTeam(converter.teamDTOToEntity(teamDTO));
    }

    @GetMapping("/crud")
    public List<TeamDTO> getTeams(){
        return converter.listTeamEntityToDTO(teamService.getTeams());
    }

    @PutMapping(path = "/crud/{teamName}")
    public void updatePlayer(
            @PathVariable("teamName") String teamName,
            @RequestParam(required = false) String newName
    ){
        teamService.updateTeam(teamName,newName);
    }

    @DeleteMapping(path = "/crud/{teamName}")
    public void deleteTeam(@PathVariable("teamName")String teamName){
        teamService.deleteTeam(teamName);
    }

    @GetMapping(path = "/crud/{teamName}")
    public TeamDTOWithPlayers getTeam(@PathVariable("teamName")String  teamName){
        return converter.teamDTOInfo(teamService.getTeam(teamName));
    }
}
