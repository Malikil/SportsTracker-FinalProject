package com.sportstracker.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sportstracker.border.SportsDAO;
import com.sportstracker.entities.Match;
import com.sportstracker.entities.Team;

public class AdminController
{
	SportsDAO db;
	
	public AdminController()
	{
		db = new SportsDAO();
	}
	
	public List<Team> getTeamList()
	{
		return db.getAllTeams();
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
			String homeScore,
			String awayScore,
			String time)
	{
		Date matchTime;
		Integer home = null, away = null;
		try
		{ matchTime = new SimpleDateFormat("dd/MM/yyyy").parse(time); }
		catch (ParseException ex)
		{ return false; } // Matches must have a date
		
		// Parse scores
		try
		{
			home = new Integer(homeScore);
			away = new Integer(awayScore);
		}
		catch (NumberFormatException ex)
		{ /* Fail silent */ }
		
		Team hometeam = db.getTeamByName(homeTeamName);
		Team awayteam = db.getTeamByName(awayTeamName);
		
		// Make sure both teams exist
		if (hometeam != null && awayteam != null && !hometeam.equals(awayteam))
		{
			// Add the match
			Match match = new Match();
			match.setHomeTeam(hometeam);
			match.setAwayTeam(awayteam);
			if (home != null && away != null)
			{
				match.setHomeScore(home);
				match.setAwayScore(away);
			}
			match.setTime(matchTime);
			db.createMatch(match);
			
			return true;
		}
		else
			return false;
	}
}
