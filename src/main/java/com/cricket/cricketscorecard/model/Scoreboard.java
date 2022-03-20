package com.cricket.cricketscorecard.model;


import com.cricket.cricketscorecard.constant.Ball;
import com.cricket.cricketscorecard.exception.CricketScoreBoardException;
import com.cricket.cricketscorecard.exception.ExceptionType;

import java.util.List;
import java.util.Map;

public class Scoreboard {
    private final TeamBatting teamBatting;
    private Map<String, Integer> playerOrdeMap;
    private List<Batsman> playerList = null;
    private  Integer numberOfPlayers;
    private  Integer numberOfOvers = null;

    private Integer currScore = 0;

    private Integer ballsBowled = 0;

    private Integer wicketsDown = 0;

    private Boolean inningsOver = false;

    private String strikerPlayer ;

    private String nonStrikerPlayer ;


    public Scoreboard(TeamBatting teamBatting, List<Batsman> batsmen, Integer numberOfPlayers, int overs, Map<String, Integer> playerOrder) {
        this.teamBatting = teamBatting;
        this.playerList = batsmen;
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfOvers = numberOfOvers;
        this.currScore = currScore;
        this.ballsBowled = ballsBowled;
        this.wicketsDown = wicketsDown;
        this.inningsOver = inningsOver;
        String player=String.join(", ",  playerOrder.keySet());
        this.strikerPlayer = player.split(",")[0].trim();
        this.nonStrikerPlayer = player.split(",")[1].trim();
        this.playerOrdeMap=playerOrder;
    }

    private void strikeChange(){
        String onStrikePlayer = strikerPlayer;
        this.strikerPlayer = nonStrikerPlayer;
        this.nonStrikerPlayer = onStrikePlayer;
    }

    private void updateBallsBowled() {
        this.ballsBowled += 1;
    }

    public boolean validateAndUpdateBallResult(String ballResult){
        if(ballResult.equals(Ball.WIDE) || ballResult.equals(Ball.NO_BALL)){
            this.currScore++;
            return false;
        }

        //System.out.println("Striker: " + this.strikerPlayer + ", non: " + this.nonStrikerPlayer);
        playerList.get(playerOrdeMap.get(strikerPlayer)-1).incrementNumberOfBallsFaced();
        this.updateBallsBowled();
        this.updateScore(ballResult);
        if(this.getBallsBowled() %6 == 0){
            this.strikeChange();
        }

        return true;
    }

    private void updateScore(String ballResult) {
        switch(ballResult) {
            case Ball.WICKET:
                updateWicket();
                break;
            case Ball.ONE:
                currScore += 1;
                playerList.get(playerOrdeMap.get(strikerPlayer)-1).updateScore(1);
                this.strikeChange();
                break;
            case Ball.TWO:
                currScore += 2;
                playerList.get(playerOrdeMap.get(strikerPlayer)-1).updateScore(2);
                break;
            case Ball.THREE:
                currScore += 3;
                playerList.get(playerOrdeMap.get(strikerPlayer)-1).updateScore(3);
                this.strikeChange();
                break;
            case Ball.FOUR:
                currScore += 4;
                playerList.get(playerOrdeMap.get(strikerPlayer)-1).updateScore(4);
                playerList.get(playerOrdeMap.get(strikerPlayer)-1).incrementNumberOf4s();
                break;
            case Ball.SIX:
                currScore += 6;
                playerList.get(playerOrdeMap.get(strikerPlayer)-1).updateScore(6);
                playerList.get(playerOrdeMap.get(strikerPlayer)-1).incrementNumberOf6s();
                break;
            default:
                throw new CricketScoreBoardException(ExceptionType.INVALID_BALL_OUTCOME, "invalid ball result:" +
                        ballResult);
        }
    }

    private void updateWicket() {
        this.wicketsDown++;
        //System.out.println("wicketsDown= "+this.wicketsDown+" numberOfPlayers= "+this.numberOfPlayers);
        if(this.wicketsDown == this.numberOfPlayers - 1){
            inningsOver = true;
        } else {
            // strikerPlayer = Math.max(strikerPlayer, nonStrikerPlayer) + 1;
            int nextStriker= Math.max(playerOrdeMap.get(strikerPlayer), playerOrdeMap.get(nonStrikerPlayer)) + 1;
            this.strikerPlayer=getNextPlayer(nextStriker);
        }
    }

    private String getNextPlayer(int nextStriker) {
        for (Map.Entry<String,Integer> player:playerOrdeMap.entrySet())
        {
            if(player.getValue()==nextStriker)
                return player.getKey();
        }
        return null;
    }



    public TeamBatting getTeamBatting() {
        return teamBatting;
    }

    public List<Batsman> getPlayerList() {
        return playerList;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Integer getNumberOfOvers() {
        return numberOfOvers;
    }

    public Integer getCurrScore() {
        return currScore;
    }

    public void setCurrScore(Integer currScore) {
        this.currScore = currScore;
    }

    public Integer getBallsBowled() {
        return ballsBowled;
    }

    public void setBallsBowled(Integer ballsBowled) {
        this.ballsBowled = ballsBowled;
    }

    public Integer getWicketsDown() {
        return wicketsDown;
    }

    public void setWicketsDown(Integer wicketsDown) {
        this.wicketsDown = wicketsDown;
    }

    public Boolean getInningsOver() {
        return inningsOver;
    }

    public void setInningsOver(Boolean inningsOver) {
        this.inningsOver = inningsOver;
    }

    public String getStrikerPlayer() {
        return strikerPlayer;
    }

    public void setStrikerPlayer(String strikerPlayer) {
        this.strikerPlayer = strikerPlayer;
    }

    public String getNonStrikerPlayer() {
        return nonStrikerPlayer;
    }

    public void setNonStrikerPlayer(String nonStrikerPlayer) {
        this.nonStrikerPlayer = nonStrikerPlayer;
    }
}
