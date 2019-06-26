package com.sportstracker.entities;

import java.util.ArrayList;

import javax.persistence.*;

public class Team
{
	@Id
	@GeneratedValue
	private int id;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="team")
	private ArrayList<Player> players;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="homeTeam")
	private ArrayList<Match> homeMatches;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="awayTeam")
	private ArrayList<Match> awayMatches;
	
	@Transient
	private ArrayList<Match> matches;
	
	public int getId() {
		return id;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public ArrayList<Match> getHomeMatches() {
		return homeMatches;
	}
	public ArrayList<Match> getAwayMatches() {
		return awayMatches;
	}
	
	public int getWinCount()
	{
		int wins = 0;
		for (Match match : getMatches())
			if (match.getWinner() == this)
				wins++;
		return wins;
	}
	
	public int getMatchCount()
	{
		return getMatches().size();
	}
	
	public ArrayList<Match> getMatches()
	{
		if (matches == null)
		{
			matches = new ArrayList<>(homeMatches.size() + awayMatches.size());
			matches.addAll(homeMatches);
			matches.addAll(awayMatches);
		}
		return matches;
	}
}
