package com.cricket.cricketscorecard.controller;

import com.cricket.cricketscorecard.model.MatchResult;
import com.cricket.cricketscorecard.model.Scoreboard;
import com.cricket.cricketscorecard.service.DisplayScoreBoard;
import com.cricket.cricketscorecard.service.InningsService;

import java.util.Scanner;

public class InningsController {

    private Scoreboard scoreboard;
    private InningsService inningsService;

    public InningsController(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
        this.inningsService= new InningsService(scoreboard);
    }

    public void startInnings(int overs) {
        inningsService.startInningsService(overs);
    }

    public void startSecondInnings(Integer target, int numberOfPlayers, int overs) {
        inningsService.startSecondInningsService( target,  numberOfPlayers,  overs,  scoreboard);
    }
}
