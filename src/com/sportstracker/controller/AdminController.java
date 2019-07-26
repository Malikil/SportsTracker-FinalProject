package com.sportstracker.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sportstracker.border.SportsDAO;
import com.sportstracker.entities.Match;
import com.sportstracker.entities.Player;
import com.sportstracker.entities.Team;

public class AdminController
{
	SportsDAO db;
	
	/**
	 * Defines DAO Object for use
	 */
	public AdminController()
	{
		db = new SportsDAO();
	}
	
	public List<Team> getTeamList()
	{
		return db.getAllTeams();
	}
	
	/**
	 * 
	 * @param pFirstName First name of player
	 * @param pLastName Last name of player
	 * @param position Player Position
	 * @param teamName Player's team name
	 * @param jerseyNumber Player's jersey number 
	 * @param age Player age
	 * @param weight Player weight
	 * @param height player height
	 * @param activePlayer Indicates if a player is still actively playing or not
	 * @return reutrn's if add was successful or not
	 */
	public boolean addNewPlayer(
			String pFirstName,
			String pLastName,
			String position,
			String teamName,
			int jerseyNumber,
			int age,
			int height)
	{
		if(db.getPlayerByName(pFirstName) == null)
		{
			Player play = new Player();
			
			play.setPFirstName(pFirstName);
			play.setPLastName(pLastName);
			play.setPosition(position);
			play.setTeam(db.getTeamByName(teamName));
			play.setJerseyNumber(jerseyNumber);
			play.setAge(age);
			play.setHeight(height);
			
			if (db.createPlayer(play) != null)
				return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param teamName Name of the team
	 * @return reutrn's if add was successful or not
	 */
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
	
	/**
	 * 
	 * @param homeTeamName Name of home team
	 * @param awayTeamName Name of away team
	 * @param home Score Score of home team
	 * @param away Score Score of away tea,
	 * @param time Time of the game
	 * @return reutrn's if add was successful or not
	 */
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
