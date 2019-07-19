package com.sportstracker.entities;

import javax.swing.JPanel;

public class MatchTab extends CloseableTab 
{
	JPanel panel;
	
	public MatchTab (Match match)
	{
		super(match.getHomeTeam().getTeamName()+ " VS " + match.getAwayTeam().getTeamName());
		
	}
	public void initialize(Match match)
	{
		
	}
}
