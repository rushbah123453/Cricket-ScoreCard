package com.cricket.cricketscorecard.controller;

import com.cricket.cricketscorecard.model.Batsman;
import com.cricket.cricketscorecard.model.Scoreboard;
import com.cricket.cricketscorecard.model.TeamBatting;
import com.cricket.cricketscorecard.service.ScoreBoardService;

import java.util.*;

public class ScoreBoardController {



    public Scoreboard getScoreBoard(MatchController match, TeamBatting team) {
        ScoreBoardService scoreBoardService=new ScoreBoardService();
        return scoreBoardService.getScoreBoard(match,team);
    }

}
