package com.sportstracker.controller;

import com.sportstracker.border.SportTrackerMain;
import com.sportstracker.border.SportsDAO;
import com.sportstracker.entities.*;
/**
 * This class will handle the creation of new tabs
 */
public class TabController
{
	private SportsDAO db;
	private SportTrackerMain context;
	
	public TabController(SportTrackerMain context)
	{
		this.context = context;
		db = new SportsDAO();
	}
	
	public TeamTab getNewTeamTab(String team)
	{ return getNewTeamTab(db.getTeamByName(team)); }
	public TeamTab getNewTeamTab(Team team)
	{
		return new TeamTab(context, team);
	}
	
	public MatchTab getNewMatchTab(int matchid)
	{ return getNewMatchTab(db.getMatchByID(matchid)); }
	public MatchTab getNewMatchTab(Match match)
	{
		return new MatchTab(match);
	}
}

