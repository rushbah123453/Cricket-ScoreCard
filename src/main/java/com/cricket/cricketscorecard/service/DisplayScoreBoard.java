package com.cricket.cricketscorecard.service;

import com.cricket.cricketscorecard.model.Scoreboard;

public class DisplayScoreBoard {
    private Scoreboard scoreboard;
    public DisplayScoreBoard(Scoreboard scoreboard){
        this.scoreboard=scoreboard;
    }

    public void showBatsmanScoreBoard() {
        displayScoreByPlayers(scoreboard);
        displayTotalScore(scoreboard);
        displayOversBowled(scoreboard);
        System.out.println();
        System.out.println();
    }

    private void displayScoreByPlayers(Scoreboard scoreboard) {
        System.out.println("Scorecard for Team "+ scoreboard.getTeamBatting().getValue() + ":");
        System.out.println("Player Name " + "   " + "Score " + "    " + "4s " +  "  " + "6s " + "  " + "Balls ");
        scoreboard.getPlayerList().forEach(player -> {
            String currentPlayer=" ";
            if(player.getName().equals(scoreboard.getStrikerPlayer()) || player.getName().equals(scoreboard.getNonStrikerPlayer())){
                currentPlayer="*";
            }
            System.out.println(player.getName() +currentPlayer+ "              " + player.getScore() + "       " + player.getNumberOf4s() + "     " +
                    player.getNumberOf6s() + "    " + player.getNumberOfBallsFaced());
        });
    }

    private void displayTotalScore(Scoreboard scoreboard) {
        System.out.println("Total: " + scoreboard.getCurrScore() + "/" + scoreboard.getWicketsDown());
    }

    private void displayOversBowled(Scoreboard scoreboard) {
        if(scoreboard.getBallsBowled() % 6 == 0){
            System.out.println("Overs: " + scoreboard.getBallsBowled()/6);
        } else {
            int oversBowled = this.scoreboard.getBallsBowled()/ 6;
            int ballsBowledForCurrOver = scoreboard.getBallsBowled() % 6;
            System.out.println("Overs: " + oversBowled + "." + ballsBowledForCurrOver);
        }
    }
}
