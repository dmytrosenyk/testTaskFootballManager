package com.senyk.spring.footballRest.dto;

import com.senyk.spring.footballRest.dao.TeamRepository;
import com.senyk.spring.footballRest.entity.Player;
import com.senyk.spring.footballRest.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Converter {
    private final TeamRepository teamRepository;

    @Autowired
    public Converter(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    /**
     *CONVERTER FOR PLAYER
     */
    public PlayerDTO playerEntityToDTO(Player player){
        PlayerDTO playerDTO=new PlayerDTO();
        playerDTO.setName(player.getName());
        playerDTO.setDateOfBirth(player.getDateOfBirth());
        playerDTO.setDateOfStartCareer(player.getDateOfStartCareer());
        playerDTO.setTeam(player.getTeam().getName());
        return playerDTO;
    }
    public List<PlayerDTO> listPlayerEntityToDTO(List<Player> playerList){
        return playerList.stream().map(this::playerEntityToDTO).collect(Collectors.toList());
    }
    public Player playerDTOToEntity(PlayerDTO playerDTO){
        Optional<Team> optionalTeam = teamRepository.findTeamByName(playerDTO.getTeam());
        if (optionalTeam.isEmpty()){
            throw new IllegalStateException("team not found");
        }
        Player player=new Player();
        player.setName(playerDTO.getName());
        player.setDateOfBirth(playerDTO.getDateOfBirth());
        player.setDateOfStartCareer(playerDTO.getDateOfStartCareer());
        player.setTeam(optionalTeam.get());
        return player;
    }
    public List<Player> listPlayerDTOToEntity(List<PlayerDTO> playerDTOList){
        return playerDTOList.stream().map(this::playerDTOToEntity).collect(Collectors.toList());
    }

    /**
     *CONVERTER FOR TEAM
     */
    public TeamDTO teamEntityToDTO(Team team){
        TeamDTO teamDTO=new TeamDTO();
        teamDTO.setName(team.getName());
        teamDTO.setCommission(team.getCommission());
        teamDTO.setBudget(team.getBudget());
        teamDTO.setDateOfCreate(team.getDateOfCreate());
        return teamDTO;
    }
    public List<TeamDTO> listTeamEntityToDTO(List<Team> teamList){
        return teamList.stream().map(this::teamEntityToDTO).collect(Collectors.toList());
    }
    public Team teamDTOToEntity(TeamDTO teamDTO){
        Team team=new Team();
        team.setName(teamDTO.getName());
        team.setCommission(teamDTO.getCommission());
        team.setBudget(teamDTO.getBudget());
        team.setDateOfCreate(teamDTO.getDateOfCreate());
        return team;
    }
    public List<Team> listTeamDTOToEntity(List<TeamDTO> teamDTOList){
        return teamDTOList.stream().map(this::teamDTOToEntity).collect(Collectors.toList());
    }

    public TeamDTOWithPlayers teamDTOInfo(Team team){
        Optional<Team> optionalTeam = teamRepository.findTeamByName(team.getName());
        if (optionalTeam.isEmpty()){
            throw new IllegalStateException("team not found");
        }
        TeamDTO teamDTO=teamEntityToDTO(team);
        List<String> players= team.getPlayers().stream().map(Player::getName).collect(Collectors.toList());

        return new TeamDTOWithPlayers(teamDTO,players);
    }
}
