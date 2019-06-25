package com.sportstracker.entities;

import java.util.Date;
//import java.util.ArrayList;

import javax.persistence.*;

@Entity
@Table(name="Match")
public class Match
{
	@Id
	@GeneratedValue
	private int Id;
	@ManyToOne
	private Team winner;
	@ManyToOne
	private Team loser;
	private int winnerScore;
	private int loserScore;
	private String location;
	private Date time;
	// Leaving yellow/red cards out for now
	
	public Team getWinner() {
		return winner;
	}
	public void setWinner(Team winner) {
		this.winner = winner;
	}
	public Team getLoser() {
		return loser;
	}
	public void setLoser(Team loser) {
		this.loser = loser;
	}
	public int getWinnerScore() {
		return winnerScore;
	}
	public void setWinnerScore(int winnerScore) {
		this.winnerScore = winnerScore;
	}
	public int getLoserScore() {
		return loserScore;
	}
	public void setLoserScore(int loserScore) {
		this.loserScore = loserScore;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getId() {
		return Id;
	}
	public boolean isDraw()	{
		return winnerScore == loserScore;
	}
}
