package com.sportstracker.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.sportstracker.border.SportsDAO;
import com.sportstracker.border.adminwin.*;
import com.sportstracker.entities.Match;
import com.sportstracker.entities.Player;
import com.sportstracker.entities.Team;

public class AdminController
{
	SportsDAO db;
	
	public Boolean createNewPlayer()
	{
		ArrayList<String> teamNames = new ArrayList<>();
		for (Team t : db.getAllTeams())
			teamNames.add(t.getTeamName());
		AdminPlayerDiag diag = new AdminPlayerDiag(teamNames);
		if (diag.showDialog())
		{
			// Add player
			try
			{
				Player p = new Player();
				p.setTeam(db.getTeamByName(diag.getTeam()));
				p.setAge(diag.getAge());
				p.setHeight(diag.getHeight());
				p.setJerseyNumber(diag.getJersey());
				p.setFirstName(diag.getFirstName());
				p.setLastName(diag.getLastName());
				p.setPosition(diag.getPosition());
				return db.createPlayer(p) != null;
			}
			catch (HibernateException |
					NumberFormatException ex)
			{
				return false;
			}
		}
		return null;
	}
	
	public Boolean updatePlayer(String playerFirstLast)
	{
		ArrayList<String> teamNames = new ArrayList<>();
		for (Team t : db.getAllTeams())
			teamNames.add(t.getTeamName());
		Player p = db.getPlayerByName(playerFirstLast);
		AdminPlayerDiag diag = new AdminPlayerDiag(teamNames, p);
		if (diag.showDialog())
		{
			// Update player
			try
			{
				p.setTeam(db.getTeamByName(diag.getTeam()));
				p.setAge(diag.getAge());
				p.setHeight(diag.getHeight());
				p.setJerseyNumber(diag.getJersey());
				p.setFirstName(diag.getFirstName());
				p.setLastName(diag.getLastName());
				p.setPosition(diag.getPosition());
				return db.updatePlayer(p);
			}
			catch (HibernateException |
					NumberFormatException ex)
			{
				return false;
			}
		}
		return null;
	}
	
	public Boolean createNewMatch()
	{
		ArrayList<String> teamNames = new ArrayList<>();
		ArrayList<Team> teams = db.getAllTeams();
		for (Team t : teams)
			teamNames.add(t.getTeamName());
		AdminMatchDiag diag = new AdminMatchDiag(teamNames);
		if (diag.showDialog())
		{
			// Add match
			try
			{
				Match m = new Match();
				Team t = null;
				for (Team search : teams)
					if (search.getTeamName().equals(diag.getHomeTeam()))
						{
							t = search;
							break;
						}
				m.setHomeTeam(t);
				for (Team search : teams)
					if (search.getTeamName().equals(diag.getAwayTeam()))
						{
							t = search;
							break;
						}
				m.setAwayTeam(t);
				m.setHomeScore(diag.getHomeScore());
				m.setAwayScore(diag.getAwayScore());
				m.setTime(diag.getDate());
				
				return db.createMatch(m) != null;
			}
			catch (ParseException
					| NumberFormatException
					| HibernateException ex)
			{
				return false;
			}
		}
		return null;
	}
	
	public Boolean updateMatch(String homeTeamName, String awayTeamName, String date)
	{
		// Get match
		Match m;
		try
		{
			m = db.findMatch(
					homeTeamName,
					awayTeamName,
					new SimpleDateFormat("dd/MM/yyyy").parse(date));
		}
		catch (ParseException ex)
		{
			// Still show the dialog, just don't preload fields
			m = new Match();
		}
		// Get team list
		ArrayList<String> teamNames = new ArrayList<>();
		ArrayList<Team> teams = db.getAllTeams();
		for (Team t : teams)
			teamNames.add(t.getTeamName());
		
		AdminMatchDiag diag = new AdminMatchDiag(teamNames, m);
		if (diag.showDialog())
		{
			try
			{
				Team t = null;
				for (Team search : teams)
					if (search.getTeamName().equals(diag.getHomeTeam()))
						{
							t = search;
							break;
						}
				m.setHomeTeam(t);
				for (Team search : teams)
					if (search.getTeamName().equals(diag.getAwayTeam()))
						{
							t = search;
							break;
						}
				m.setAwayTeam(t);
				m.setHomeScore(diag.getHomeScore());
				m.setAwayScore(diag.getAwayScore());
				m.setTime(diag.getDate());
				
				return db.updateMatch(m);
			}
			catch (ParseException ex)
			{
				return false;
			}
		}
		return null;
	}
	
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
	 * @deprecated Use createNewPlayer() instead to show a dialog box which will gather info
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
		if(db.getPlayerByName(pFirstName + " " +pLastName) == null)
		{
			Player play = new Player();
			
			play.setFirstName(pFirstName);
			play.setLastName(pLastName);
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
	 * @deprecated Use createNewMatch() instead to show a dialog box which will gather info
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
