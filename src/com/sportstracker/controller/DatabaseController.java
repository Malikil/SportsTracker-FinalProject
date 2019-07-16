package com.sportstracker.controller;

import java.util.ArrayList;

import com.sportstracker.border.SportsDAO;
import com.sportstracker.entities.Match;
import com.sportstracker.entities.Team;

public class DatabaseController
{
	private SportsDAO db;
	
	public DatabaseController()
	{
		db = new SportsDAO();
	}
	
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
	
	public ArrayList<Match> getPastTeamMatches(Team team)
	{
		// TODO not implemented yet
		return null;
	}
}
