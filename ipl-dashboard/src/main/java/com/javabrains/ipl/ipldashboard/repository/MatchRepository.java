package com.javabrains.ipl.ipldashboard.repository;

import java.util.List;

import com.javabrains.ipl.ipldashboard.batchprocessing.model.MatchData;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<MatchData, Long>{

    List<MatchData> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    default List<MatchData> findLatestMatchesbyTeam(String teamName, int count){
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, Pageable.ofSize(count));
    }

}
