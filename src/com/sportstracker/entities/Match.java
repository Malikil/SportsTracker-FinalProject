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
	private Team homeTeam;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Team awayTeam;
	
	private int homeScore;
	private int awayScore;
	private String location;
	private Date time;
	// Leaving yellow/red cards out for now
	
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team team) {
		this.homeTeam = team;
	}
	public Team getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(Team team) {
		this.awayTeam = team;
	}
	public int getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(int score) {
		this.homeScore = score;
	}
	public int getAwayScore() {
		return awayScore;
	}
	public void setAwayScore(int score) {
		this.awayScore = score;
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
