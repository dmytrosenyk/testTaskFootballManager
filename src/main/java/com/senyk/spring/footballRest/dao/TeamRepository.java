package com.senyk.spring.footballRest.dao;

import com.senyk.spring.footballRest.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository
        extends JpaRepository<Team,Long> {

    Optional<Team> findTeamByName(String teamName);
}
