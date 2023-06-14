package com.senyk.spring.footballRest.controller;

import com.senyk.spring.footballRest.dto.*;
import com.senyk.spring.footballRest.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/player")
public class PlayerController {
    private final PlayerService playerService;
    private final Converter converter;

    @Autowired
    public PlayerController(PlayerService playerService, Converter converter) {
        this.playerService = playerService;
        this.converter = converter;
    }

    @GetMapping("/crud")
    public List<PlayerDTO> getPlayers(){
        return converter.listPlayerEntityToDTO(playerService.getPlayers());
    }

    @PostMapping("/crud")
    public void addNewPlayer(@RequestBody PlayerDTO playerDTO){
        playerService.addNewPlayer(converter.playerDTOToEntity(playerDTO));
    }

    @DeleteMapping(path = "/crud/{playerName}")
    public void deletePlayer(@PathVariable("playerName")String playerName){
        playerService.deletePlayer(playerName);
    }

    @PutMapping(path = "/crud/{playerName}")
    public void updatePlayer(
        @PathVariable("playerName") String playerName,
        @RequestParam String nameOfNewTeam
    ){
        playerService.updatePlayer(playerName,nameOfNewTeam);
    }
}
