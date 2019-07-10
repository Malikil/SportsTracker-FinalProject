package com.sportstracker.controller;

import java.util.Date;

import com.sportstracker.border.SportsDAO;
import com.sportstracker.entities.Team;

public class AdminController
{
	SportsDAO db;
	
	public AdminController()
	{
		db = new SportsDAO();
	}
	
	public boolean addNewPlayer(
			String pFirstName,
			String pLastName,
			String position,
			String teamName,
			int jerseyNumber,
			int age,
			int weight,
			int height,
			boolean activePlayer)
	{
		
		return false;
	}
	
	public boolean addNewTeam(String teamName)
	{
		// Make sure the team doesn't already exist
		if (db.getTeamByName(teamName) == null)
		{
			Team team = new Team();
			team.setTeamName(teamName);
			
			if (db.createTeam(team) != null)
				return true;
		}
		return false;
	}
	
	public boolean addNewMatch(
			String homeTeamName,
			String awayTeamName,
			int homeScore,
			int awayScore,
			Date time)
	{
		
		return false;
	}
}
