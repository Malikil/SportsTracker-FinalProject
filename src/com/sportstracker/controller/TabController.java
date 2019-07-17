package com.sportstracker.controller;

import com.sportstracker.border.SportsDAO;
import com.sportstracker.entities.*;
/**
 * This class will handle the creation of new tabs
 */
public class TabController
{
	private SportsDAO db;
	public TabController()
	{
		db = new SportsDAO();
	}
	
	public TeamTab getNewTeamTab(String team)
	{ return getNewTeamTab(db.getTeamByName(team)); }
	public TeamTab getNewTeamTab(Team team)
	{
		return new TeamTab(team);
	}
}
