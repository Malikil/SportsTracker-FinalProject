package com.sportstracker.entities;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;

@Entity
@Table(name="Teams")
public class Team
{
	@Id
	@GeneratedValue
	private int id;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="team")
	private List<Player> players;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="homeTeam")
	private List<Match> homeMatches;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="awayTeam")
	private List<Match> awayMatches;
	
	@Transient
	private ArrayList<Match> matches;
	
	private String teamName;
	private String teamCaptain;
	private String teamMVP;
	
	
	public int getId() {
		return id;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public List<Match> getHomeMatches() {
		return homeMatches;
	}
	public List<Match> getAwayMatches() {
		return awayMatches;
	}
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamCaptain() {
		return teamCaptain;
	}
	public void setTeamCaptain(String teamCaptain) {
		this.teamCaptain = teamCaptain;
	}
	public String getTeamMVP() {
		return teamMVP;
	}
	public void setTeamMVP(String teamMVP) {
		this.teamMVP = teamMVP;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public void setHomeMatches(List<Match> homeMatches) {
		this.homeMatches = homeMatches;
	}
	public void setAwayMatches(List<Match> awayMatches) {
		this.awayMatches = awayMatches;
	}
	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
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
