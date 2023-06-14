package com.senyk.spring.footballRest.service;

import com.senyk.spring.footballRest.dao.PlayerRepository;
import com.senyk.spring.footballRest.dao.TeamRepository;
import com.senyk.spring.footballRest.entity.Player;
import com.senyk.spring.footballRest.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    @Autowired
    public PlayerService(PlayerRepository playerRepository,
                         TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    /**
     * CREATE
     */
    @Transactional
    public void addNewPlayer(Player player) {
        Optional<Player>optionalPlayer= playerRepository.findPlayerByName(player.getName());
        if (optionalPlayer.isPresent()){
            throw new IllegalStateException("player already created");
        }
        playerRepository.save(player);
    }

    /**
     * READ
     */
    @Transactional
    public List<Player> getPlayers(){
        return playerRepository.findAll();
    }

    /**
     * DELETE
     */
    @Transactional
    public void deletePlayer(String name) {
        Optional<Player>optionalPlayer= playerRepository.findPlayerByName(name);
        if (optionalPlayer.isEmpty()){
            throw new IllegalStateException("player not found");
        }
        playerRepository.deleteById(optionalPlayer.get().getId());
    }

    /**
     * UPDATE
     */
    @Transactional
    public void updatePlayer(String name, String newTeamName) {
        Optional<Player> optionalPlayer=playerRepository.findPlayerByName(name);
        if(optionalPlayer.isEmpty()){
            throw new IllegalStateException("player with name "+name+" does not exists");
        }
        Player player=optionalPlayer.get();
        System.out.println("age="+player.getAge());
        System.out.println("experience="+player.getExperience());

        if(Objects.equals(player.getTeam().getName(), newTeamName)){
            throw new IllegalStateException("player already is on that team");
        }

        Optional<Team> optionalTeam =teamRepository.findTeamByName(newTeamName);
        if(optionalTeam.isEmpty()){
            throw new IllegalStateException("team:"+newTeamName+" does not exists");
        }
        Team newTeam=optionalTeam.get();
        Team oldTeam=player.getTeam();

        BigDecimal transit=TransitHelpClass.transit(player.getExperience(),player.getAge());
        BigDecimal commission=TransitHelpClass.commission(transit,player.getTeam().getCommission());
        BigDecimal sum=TransitHelpClass.sum(transit,commission);

        System.out.println("balance team 1="+oldTeam.getBudget());
        System.out.println("balance team 2="+newTeam.getBudget());

        System.out.println("transit="+transit);
        System.out.println("commission="+commission);
        System.out.println("sum="+sum);


        if ( !(sum.compareTo(newTeam.getBudget())>0)) {
                BigDecimal newBudgetPreviousTeam = player.getTeam().getBudget().add(sum);
                BigDecimal newBudgetNewTeam = newTeam.getBudget().subtract(sum);

                player.getTeam().setBudget(newBudgetPreviousTeam);
                newTeam.setBudget(newBudgetNewTeam);

                player.setTeam(newTeam);
        }else throw new IllegalStateException("team "+newTeam+" does not have enough money");

        System.out.println("balance team 1="+oldTeam.getBudget());
        System.out.println("balance team 2="+newTeam.getBudget());

        teamRepository.save(oldTeam);
        teamRepository.save(newTeam);
        playerRepository.save(player);
    }
}
