package com.cricket.cricketscorecard.service;

import com.cricket.cricketscorecard.model.MatchResult;
import com.cricket.cricketscorecard.model.Scoreboard;

import java.util.Scanner;

public class InningsService {


    private Scoreboard scoreboard;
    private Scanner sc = new Scanner(System.in);
    public InningsService(Scoreboard scoreboard) {
        this.scoreboard=scoreboard;
    }

    public void startInningsService(int overs) {
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
        }
    }


    public void startSecondInningsService(Integer target, int numberOfPlayers, int overs, Scoreboard scoreboardB) {
        DisplayScoreBoard displayScoreBoard = new DisplayScoreBoard(scoreboardB);
        for (int i = 0; i < overs; i++) {
            System.out.println("Over " + (i + 1) + ":");
            int validDeliveries = 0;

            while (validDeliveries != 6) {
                String ballResult = sc.next();
                if (scoreboardB.validateAndUpdateBallResult(ballResult)) {
                    validDeliveries++;
                }
                if (scoreboardB.getCurrScore() > target) {
                    displayScoreBoard.showBatsmanScoreBoard();
                    System.out.println(MatchResult.TEAM_2_WINS.getValue() + " by " +
                            (numberOfPlayers - scoreboardB.getWicketsDown()) + " wickets.");
                    return;
                }
                if (scoreboardB.getInningsOver()) {
                    displayScoreBoard.showBatsmanScoreBoard();
                    System.out.println(MatchResult.TEAM_1_WINS.getValue() + " by " +
                            (this.scoreboard.getCurrScore() - scoreboardB.getCurrScore()) + " runs.");
                    return;
                }
            }

            displayScoreBoard.showBatsmanScoreBoard();
        }
        if (scoreboardB.getCurrScore().equals(target)) {
            System.out.println(MatchResult.MATCH_DRAWN.getValue() + " due to same score");
        } else {
            System.out.println(MatchResult.TEAM_1_WINS.getValue() + " by " +
                    (this.scoreboard.getCurrScore() - scoreboardB.getCurrScore()) + " runs.");
        }
    }
}
