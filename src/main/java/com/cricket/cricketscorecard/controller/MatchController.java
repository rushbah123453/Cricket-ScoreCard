package com.cricket.cricketscorecard.controller;

import java.util.Scanner;

public class MatchController {
    private int overs;
    private int numberOfPlayers;

    public int getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void startMatch() {
        System.out.println("Enter the number of players for each team:");
        Scanner sc = new Scanner(System.in);
         this.numberOfPlayers = sc.nextInt();
        System.out.println("Enter the number of overs");
         this.overs = sc.nextInt();
    }
}
