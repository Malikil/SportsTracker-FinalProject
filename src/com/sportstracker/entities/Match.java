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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="matches")
	private Team homeTeam;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="matches")
	private Team awayTeam;
	
	private int homeScore;
	private int awayScore;
	private String location;
	private Date time;
	// Leaving yellow/red cards out for now
	
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team winner) {
		this.homeTeam = winner;
	}
	public Team getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(Team loser) {
		this.awayTeam = loser;
	}
	public int getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(int winnerScore) {
		this.homeScore = winnerScore;
	}
	public int getAwayScore() {
		return awayScore;
	}
	public void setAwayScore(int loserScore) {
		this.awayScore = loserScore;
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
		return homeScore == awayScore;
	}

	public Team getWinner()
	{
		if (homeScore > awayScore)
			return homeTeam;
		else if (awayScore > homeScore)
			return awayTeam;
		else
			return null;
	}
	public Team getLoser()
	{
		if (homeScore < awayScore)
			return homeTeam;
		else if (awayScore < homeScore)
			return awayTeam;
		else
			return null;
	}
	public int getWinnerScore()
	{
		if (homeScore > awayScore)
			return homeScore;
		else
			return awayScore;
	}
	public int getLoserScore()
	{
		if (homeScore < awayScore)
			return homeScore;
		else
			return awayScore;
	}
}
