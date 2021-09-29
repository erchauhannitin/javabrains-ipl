package com.javabrains.ipl.ipldashboard.controller;

import com.javabrains.ipl.ipldashboard.batchprocessing.model.TeamData;
import com.javabrains.ipl.ipldashboard.repository.MatchRepository;
import com.javabrains.ipl.ipldashboard.repository.TeamRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public TeamController(MatchRepository matchRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/team")
    public Iterable<TeamData> getAllTeam() {
        return this.teamRepository.findAll();
    }

    @GetMapping("/teams/{teamName}")
    public TeamData getTeam(@PathVariable String teamName){
        
        TeamData team = this.teamRepository.findByTeamName(teamName);
        team.setMatches(matchRepository.findLatestMatchesbyTeam(teamName,4));
            
        return team;        
    }
}
