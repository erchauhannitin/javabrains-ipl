package com.javabrains.ipl.ipldashboard.repository;

import com.javabrains.ipl.ipldashboard.batchprocessing.model.TeamData;

import org.springframework.data.repository.CrudRepository;


public interface TeamRepository extends CrudRepository<TeamData, Long>  {

    TeamData findByTeamName(String teamName);
    
}
