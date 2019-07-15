package com.sportstracker.controller;

import java.util.ArrayList;

import com.sportstracker.border.SportsDAO;
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
}
