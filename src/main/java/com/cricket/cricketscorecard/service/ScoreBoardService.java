package com.cricket.cricketscorecard.service;

import com.cricket.cricketscorecard.controller.MatchController;
import com.cricket.cricketscorecard.model.Batsman;
import com.cricket.cricketscorecard.model.Scoreboard;
import com.cricket.cricketscorecard.model.TeamBatting;

import java.util.*;

public class ScoreBoardService {
    public Scoreboard getScoreBoard(MatchController match, TeamBatting team) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Batting order for team ");
        Map<String,Integer> playerOrder = new LinkedHashMap<>();
        List<String> playerListA=new ArrayList<>();
        for(int i = 0; i < match.getNumberOfPlayers(); i++){
            String player=sc.next();
            playerOrder.put(player,i+1);
            playerListA.add(player);
        }
        Scoreboard scoreboard = getScoreBoardForTeam(match.getNumberOfPlayers(), playerOrder, team,match.getOvers());
        return scoreboard;
    }
    private static Scoreboard getScoreBoardForTeam(Integer numberOfPlayers, Map<String, Integer> playerOrder,
                                                   TeamBatting teamBatting, int overs){
        List<Batsman> batsmen = new ArrayList<>();
        for(Map.Entry<String,Integer> playerOrderMap:playerOrder.entrySet()){
            batsmen.add(new Batsman(playerOrderMap.getKey()));
        }
        return new Scoreboard(teamBatting,batsmen,numberOfPlayers,overs,playerOrder);
    }
}
