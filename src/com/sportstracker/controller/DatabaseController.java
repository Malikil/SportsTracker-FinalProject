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
		// Just give back an empty set if there are no teams to search
		if (teams.size() < 1)
			return new ArrayList<Match>();
		
		// Create a list for the matching matches
		ArrayList<Match> results = new ArrayList<>();
		// Get a list of the match lists for all needed teams
		ArrayList<List<Match>> matches = new ArrayList<List<Match>>(teams.size());
		for (int i = 0; i < teams.size(); i++)
			matches.add(getPastTeamMatches(teams.get(i)));
		// Limiting to 5 items atm
		for (int i = 0; i < 5; i++)
		{
			// Find the most recent match
			Match closest = null;
			int list = -1;
			for (int l = 0; l < matches.size(); l++)
				if (matches.get(l).size() > 0)
					if (closest == null
							|| closest.getTime().before(matches.get(l).get(0).getTime()))
					{
						closest = matches.get(l).get(0);
						list = l;
					}
			// Remove the match from the list it was in
			if (list > -1)
			{
				matches.get(list).remove(closest);
				results.add(closest);
			}
		}
		
		return results;
	}
	
	/**
	 * @param teams Name of team which upcoming matches will be shown
	 * @return Returns List of upcoming matches for specific team(s)
	 */
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
			Match closest = null;
			int list = -1;
			for (int l = 0; l < matches.size(); l++)
				if (matches.get(l).size() > 0)
					if (closest == null
							|| closest.getTime().after(matches.get(l).get(0).getTime()))
					{
						closest = matches.get(l).get(0);
						list = l;
					}
			// Remove the match from the list it was in
			if (list > -1)
			{
				matches.get(list).remove(closest);
				results.add(closest);
			}
		}
		return results;
	}
	
	// ============================== Players ==============================
	public List<Player> getTeamPlayers(String team)
	{ return getTeamPlayers(db.getTeamByName(team)); }
	public List<Player> getTeamPlayers(Team team)
	{ return team.getPlayers(); }
	public List<Player> getAllPlayers()
	{ return db.getAllPlayers(); }
	
	// ============================== Users ==============================
	public List<String> getFollowedTeams(String user)
	{
		return db.getUser(user).getFavourites();
	}
}
