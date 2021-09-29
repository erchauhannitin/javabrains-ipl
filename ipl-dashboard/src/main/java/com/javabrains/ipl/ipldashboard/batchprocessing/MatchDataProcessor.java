package com.javabrains.ipl.ipldashboard.batchprocessing;

import java.time.LocalDate;

import com.javabrains.ipl.ipldashboard.batchprocessing.model.MatchData;

import org.springframework.batch.item.ItemProcessor;

public class MatchDataProcessor implements ItemProcessor<MatchInput, MatchData> {


  @Override
  public MatchData process(final MatchInput input) throws Exception {

    final MatchData matchData = new MatchData();
    matchData.setCity(input.getCity());
    matchData.setDate(LocalDate.parse(input.getDate()));
    matchData.setId(Long.parseLong(input.getId()));
    matchData.setPlayerOfMatch(input.getPlayer_of_match());
    matchData.setResult(input.getResult());
    matchData.setResultMargin(input.getResult_margin());
    matchData.setTossDecision(input.getToss_decision());
    matchData.setTossWinner(input.getToss_winner());
    matchData.setVenue(input.getVenue());
    matchData.setWinner(input.getWinner());
    matchData.setUmpire1(input.getUmpire1());    
    matchData.setUmpire2(input.getUmpire2());

    String firstInningsTeam, secondInningsTeam;

    if("bat".equals(input.getToss_decision())){
      firstInningsTeam = input.getToss_winner();
      secondInningsTeam = input.getToss_winner().equals(input.getTeam1()) ? input.getTeam2() : input.getTeam1();
    }
    else{
      firstInningsTeam = input.getToss_winner().equals(input.getTeam1()) ? input.getTeam2() : input.getTeam1();
      secondInningsTeam = input.getToss_winner();
    }
    matchData.setTeam1(firstInningsTeam);
    matchData.setTeam2(secondInningsTeam);

    // log.info("Converting (" + input + ") into (" + matchData + ")");

    return matchData;
  }

}