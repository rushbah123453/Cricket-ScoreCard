package com.cricket.cricketscorecard;

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
	private static Scoreboard getScoreBoardForTeam(Integer numberOfPlayers, Map<String, Integer> playerOrder,
												   TeamBatting teamBatting, int overs){
		List<Batsman> batsmen = new ArrayList<>();
		for(Map.Entry<String,Integer> playerOrderMap:playerOrder.entrySet()){
			batsmen.add(new Batsman(playerOrderMap.getKey()));
		}
		return new Scoreboard(teamBatting,batsmen,numberOfPlayers,overs,playerOrder);
	}

	public static void main(String[] args) {
		SpringApplication.run(CricketScorecardApplication.class, args);

		System.out.println("Enter the number of players for each team:");
		Scanner sc = new Scanner(System.in);
		int numberOfPlayers = sc.nextInt();
		System.out.println("Enter the number of overs");
		int overs = sc.nextInt();
		System.out.println("Batting order for team 1");
		Map<String,Integer> playerOrder = new LinkedHashMap<>();
		List<String> playerListA=new ArrayList<>();
		for(int i = 0; i < numberOfPlayers; i++){
			String player=sc.next();
			playerOrder.put(player,i+1);
			playerListA.add(player);
		}
		Scoreboard scoreboard = getScoreBoardForTeam(numberOfPlayers, playerOrder, TeamBatting.TEAMA,overs);
		boolean innigsOver=false;
		for(int i = 0; i < overs && !innigsOver; i++){
			System.out.println("Over " + (i+1) + ":");
			int validDeliveries = 0;
			while(validDeliveries != 6) {
				String ballResult = sc.next();
				if(scoreboard.validateAndUpdateBallResult(ballResult)) {
					validDeliveries++;
				}
				if (scoreboard.getInningsOver())
				{
					System.out.println("Innings Over ");
					innigsOver=true;
					break;
				}
			}
			DisplayScoreBoard displayScoreBoard=new DisplayScoreBoard(scoreboard);
			displayScoreBoard.showBatsmanScoreBoard();
			//scoreboard.showBatsmanScoreBoard();
		}

		Integer target = scoreboard.getCurrScore();
		System.out.println("Batting order for team 2");
		Map<String,Integer> playerOrderB = new LinkedHashMap<>();
		for(int i = 0; i < numberOfPlayers; i++){
			playerOrderB.put(sc.next(),i+1);
		}
		Scoreboard scoreboardB = getScoreBoardForTeam(numberOfPlayers, playerOrderB, TeamBatting.TEAMB,overs);
		DisplayScoreBoard displayScoreBoard=new DisplayScoreBoard(scoreboardB);
		for(int i = 0; i < overs; i++){
			System.out.println("Over " + (i+1) + ":");
			int validDeliveries = 0;

			while(validDeliveries != 6) {
				String ballResult = sc.next();
				if(scoreboardB.validateAndUpdateBallResult(ballResult)) {
					validDeliveries++;
				}
				if(scoreboardB.getCurrScore() > target) {
					displayScoreBoard.showBatsmanScoreBoard();
					System.out.println(MatchResult.TEAM_2_WINS.getValue() + " by " +
							(numberOfPlayers - scoreboardB.getWicketsDown()) + " wickets.");
					return;
				}
				if(scoreboardB.getInningsOver()){
					displayScoreBoard.showBatsmanScoreBoard();
					System.out.println(MatchResult.TEAM_1_WINS.getValue() + " by " +
							(scoreboard.getCurrScore() - scoreboardB.getCurrScore()) + " runs.");
					return;
				}
			}

			displayScoreBoard.showBatsmanScoreBoard();
		}
		if(scoreboardB.getCurrScore().equals(target)){
			System.out.println(MatchResult.MATCH_DRAWN.getValue() + " due to same score");
		} else {
			System.out.println(MatchResult.TEAM_1_WINS.getValue() + " by " +
					(scoreboard.getCurrScore() - scoreboardB.getCurrScore()) + " runs.");
		}

	}

}
