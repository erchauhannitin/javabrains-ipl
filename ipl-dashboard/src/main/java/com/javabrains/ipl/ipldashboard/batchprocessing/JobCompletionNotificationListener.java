package com.javabrains.ipl.ipldashboard.batchprocessing;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import com.javabrains.ipl.ipldashboard.batchprocessing.model.TeamData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final EntityManager entityManager;

  @Autowired
  public JobCompletionNotificationListener(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      Map<String, TeamData> teamData = new HashMap<>();

      entityManager.createQuery("select m.team1, count(*) from MatchData m group by m.team1", Object[].class)
          .getResultList().stream().map(e -> new TeamData((String) e[0], (long) e[1]))
          .forEach(team -> teamData.put(team.getTeamName(), team));

      entityManager.createQuery("select m.team2, count(*) from MatchData m group by m.team2", Object[].class)
          .getResultList().stream().forEach(e -> {
            TeamData team = teamData.get(e[0]);
            team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
          });

      entityManager.createQuery("select m.winner, count(*) from MatchData m group by m.winner", Object[].class)
          .getResultList().stream().forEach(e -> {
            TeamData team = teamData.get(e[0]);
            if(team != null) team.setTotalWon((long) e[1]);
          });

      teamData.values().forEach(team -> entityManager.persist(team));
      // teamData.values().forEach(team -> System.out.println(team));

    }
  }
}