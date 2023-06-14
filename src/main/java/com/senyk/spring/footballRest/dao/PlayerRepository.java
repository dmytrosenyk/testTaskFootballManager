package com.senyk.spring.footballRest.dao;

import com.senyk.spring.footballRest.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository
        extends JpaRepository<Player,Long> {

    Optional<Player> findPlayerByName(String name);
}
