package com.senyk.spring.footballRest.service;


import com.senyk.spring.footballRest.dao.TeamRepository;
import com.senyk.spring.footballRest.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    @Autowired
    public TeamService( TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    /**
     * CREATE
     */
    @Transactional
    public void addNewTeam(Team team) {
        Optional<Team>optional= teamRepository.findTeamByName(team.getName());
        if (optional.isPresent()){
            throw new IllegalStateException("team already added");
        }
        teamRepository.save(team);
    }

    /**
     * READ
     * @return list of teams
     */
    @Transactional
    public List<Team> getTeams(){
        return teamRepository.findAll();
    }

    /**
     * READ
     * @return team
     */
    @Transactional
    public Team getTeam(String teamName) {
        Optional<Team> optionalTeam = teamRepository.findTeamByName(teamName);
        if (optionalTeam.isEmpty()){
            throw new IllegalStateException("team "+teamName+" does not exists");
        }
        return optionalTeam.get();
    }

    /**
     * UPDATE
     */
    @Transactional
    public void updateTeam(String teamName,String newName) {
        Team team=teamRepository.findTeamByName(teamName).orElseThrow(
                ()->new IllegalStateException("team with name:"+teamName+" does not exists"));
        if(newName!=null || Objects.equals(teamName, newName)){
            team.setName(newName);
        }else throw new IllegalStateException("new name "+newName+" is wrong");
    }

    /**
     * DELETE
     */
    @Transactional
    public void deleteTeam(String teamName) {
        Optional<Team> optionalTeam = teamRepository.findTeamByName(teamName);
        if (optionalTeam.isEmpty()){
            throw new IllegalStateException("team "+teamName+" does not exists");
        }
        Team team=optionalTeam.get();
        if (team.getPlayers().size()!=0){
            throw new IllegalStateException("team "+teamName+" is not empty");
        }
        teamRepository.deleteById(team.getId());
    }
}
