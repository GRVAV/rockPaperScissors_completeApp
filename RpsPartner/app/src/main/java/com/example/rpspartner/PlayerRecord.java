package com.example.rpspartner;

import java.time.LocalDateTime;
import java.util.Date;

public class PlayerRecord {
    private double win;
    private double lose;
    private double draw;
    private LocalDateTime scoredOn;

    //Getters
    public double getWin() {
        return win;
    }
    public double getLose() {
        return win;
    }
    public double getDraw() {
        return win;
    }
    public double getScoredOn() {
        return win;
    }


    // Setters
    public void Win(double win) {
        this.win = win;
    }
    public void Lose(double lose) {
        this.lose = lose;
    }
    public void Draw(double draw) {
        this.draw = draw;
    }
    public void ScoredOn(LocalDateTime scoredOn) {
        this.scoredOn = scoredOn;
    }

    public PlayerRecord(){}

    public PlayerRecord(double player_wins, double player_loses, double player_draws , LocalDateTime player_scoredOn){
        this.win= player_wins;
        this.lose=player_loses;
        this.draw=player_draws;
        this.scoredOn=player_scoredOn;
    }
}
