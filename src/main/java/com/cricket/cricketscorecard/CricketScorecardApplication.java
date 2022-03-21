package com.cricket.cricketscorecard;

import com.cricket.cricketscorecard.controller.InningsController;
import com.cricket.cricketscorecard.controller.MatchController;
import com.cricket.cricketscorecard.controller.ScoreBoardController;
import com.cricket.cricketscorecard.model.Batsman;
import com.cricket.cricketscorecard.model.MatchResult;
import com.cricket.cricketscorecard.model.Scoreboard;
import com.cricket.cricketscorecard.model.TeamBatting;
import com.cricket.cricketscorecard.service.DisplayScoreBoard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class CricketScorecardApplication {


	public static void main(String[] args) {


		SpringApplication.run(CricketScorecardApplication.class, args);

		MatchController match=new MatchController();
		match.startMatch();
		ScoreBoardController scoreBoard=new ScoreBoardController();
		Scoreboard scoreBoardA=scoreBoard.getScoreBoard(match,TeamBatting.TEAMA);
		InningsController firstInnings=new InningsController(scoreBoardA);
		firstInnings.startInnings(match.getOvers());


		Integer target = scoreBoardA.getCurrScore();
		Scoreboard scoreBoardB=scoreBoard.getScoreBoard(match,TeamBatting.TEAMB);
		InningsController secondInnings=new InningsController(scoreBoardB);
		secondInnings.startSecondInnings(target,match.getNumberOfPlayers(),match.getOvers());


	}

}
