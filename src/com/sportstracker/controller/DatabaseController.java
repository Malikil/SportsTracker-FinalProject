package com.sportstracker.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sportstracker.border.SportsDAO;
import com.sportstracker.entities.Match;
import com.sportstracker.entities.Player;
import com.sportstracker.entities.Team;

public class DatabaseController
{
	private SportsDAO db;
	
	public DatabaseController()
	{
		db = new SportsDAO();
	}
	
	// ============================== Teams ==============================
	public ArrayList<String> getTeamnameList()
	{
		ArrayList<Team> teams = db.getAllTeams();
		ArrayList<String> results = new ArrayList<>(teams.size());
		for (Team t : teams)
			results.add(t.getTeamName());
		return results;
	}
	
	public ArrayList<Team> getAllTeams()
	{
		return db.getAllTeams();
	}
	
	public int getTeamRank(Team team)
	{
		// Ordering isn't guaranteed, so just go through the list and count
		// how many teams have more wins
		ArrayList<Team> teams = db.getAllTeams();
		int rank = 1;
		for (Team t : teams)
			if (t != team && t.getWinCount() > team.getWinCount())
				rank++;
		return rank;
	}
	
	// ============================== Matches ==============================
	public ArrayList<Match> getPastTeamMatches(String team)
	{ return getPastTeamMatches(db.getTeamByName(team)); }
	public ArrayList<Match> getPastTeamMatches(Team team)
	{
		ArrayList<Match> results = new ArrayList<>();
		for (Match m : db.getMatchesBeforeDate(new Date()))
			if (m.getHomeTeam().equals(team)
					|| m.getAwayTeam().equals(team))
				results.add(m);
		return results;
	}
	
	public ArrayList<Match> getUpcomingTeamMatches(String team)
	{ return getUpcomingTeamMatches(db.getTeamByName(team)); }
	public ArrayList<Match> getUpcomingTeamMatches(Team team)
	{
		ArrayList<Match> results = new ArrayList<>();
		for (Match m : db.getMatchesAfterDate(new Date()))
			if (m.getHomeTeam().equals(team)
					|| m.getAwayTeam().equals(team))
				results.add(m);
		return results;
	}
	
	public ArrayList<Match> getPastMatchesForTeamList(List<String> teams)
	{
		if (teams.size() < 1)
			return new ArrayList<Match>();
		
		ArrayList<Match> results = new ArrayList<>();
		ArrayList<List<Match>> matches = new ArrayList<List<Match>>(teams.size());
		for (int i = 0; i < teams.size(); i++)
			matches.add(getPastTeamMatches(teams.get(i)));
		// Limiting to 5 items atm
		for (int i = 0; i < 5; i++)
		{
			// Find closest date
			Match closest = matches.get(0).remove(0);
			for (int j = 1; j < matches.size(); j++)
				if (matches.get(j).get(0).getTime().after(closest.getTime()))
					closest = matches.get(j).remove(0);
			results.add(closest);
		}
		
		return results;
	}
	public ArrayList<Match> getUpcomingMatchesForTeamList(List<String> teams)
	{
		if (teams.size() < 1)
			return new ArrayList<Match>();
		
		ArrayList<Match> results = new ArrayList<>();
		ArrayList<List<Match>> matches = new ArrayList<List<Match>>(teams.size());
		for (int i = 0; i < teams.size(); i++)
			matches.add(getUpcomingTeamMatches(teams.get(i)));
		// Limiting to 5 items atm
		for (int i = 0; i < 5; i++)
		{
			// Find closest date
			Match closest = matches.get(0).remove(0);
			for (int j = 1; j < matches.size(); j++)
				if (matches.get(j).get(0).getTime().after(closest.getTime()))
					closest = matches.get(j).remove(0);
			results.add(closest);
		}
		
		return results;
	}
	
	// ============================== Players ==============================
	public List<Player> getTeamPlayers(String team)
	{ return getTeamPlayers(db.getTeamByName(team)); }
	public List<Player> getTeamPlayers(Team team)
	{ return team.getPlayers(); }
	
	// ============================== Users ==============================
	public List<String> getFollowedTeams(String user)
	{
		return db.getUser(user).getFavourites();
	}
}
