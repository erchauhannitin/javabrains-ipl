package com.javabrains.ipl.ipldashboard.batchprocessing.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class TeamData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String teamName;
    private long totalMatches;
    private long totalWon;
    @Transient
    private List<MatchData> matches;
    
    public TeamData(String teamName, long totalMatches) {
        this.teamName = teamName;
        this.totalMatches = totalMatches;
    }
    public TeamData() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public long getTotalMatches() {
        return totalMatches;
    }
    public void setTotalMatches(long totalMatches) {
        this.totalMatches = totalMatches;
    }
    public long getTotalWon() {
        return totalWon;
    }
    public void setTotalWon(long totalWon) {
        this.totalWon = totalWon;
    }    
    public List<MatchData> getMatches() {
        return matches;
    }
    public void setMatches(List<MatchData> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "TeamData [teamName=" + teamName + ", totalMatches=" + totalMatches + ", totalWon=" + totalWon + "]";
    }
    
}
