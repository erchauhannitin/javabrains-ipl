package com.javabrains.ipl.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import com.javabrains.ipl.ipldashboard.batchprocessing.model.MatchData;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends CrudRepository<MatchData, Long>{

    List<MatchData> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    List<MatchData> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(String team1, LocalDate startDate, LocalDate endDate, String team2, LocalDate startDate2, LocalDate endDate2);

    @Query("select m from MatchData m where (m.team1 = :teamName or m.team2 = :teamName) and m.date between :startDate and :endDate order by m.date desc")
    List<MatchData> getMatchesByTeamBetweenDates(
        @Param("teamName") String teamName, 
        @Param("startDate") LocalDate startDate, 
        @Param("endDate") LocalDate endDate
    );


    default List<MatchData> findLatestMatchesbyTeam(String teamName, int count){
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, Pageable.ofSize(count));
    }

}
