package com.sportstracker.entities;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sportstracker.controller.DatabaseController;

public class MatchTab extends CloseableTab 
{
	JPanel panel;
	DatabaseController dbcontrol;
	
	public MatchTab (Match match)
	{
		super(match.getHomeTeam().getTeamName()+ " VS " + match.getAwayTeam().getTeamName());
		dbcontrol = new DatabaseController();
		initialize(match);
		refreshLists();
		// Add the panel to the GridBagLayout
		addContent(panel);
	}
	
	public void initialize(Match match)
	{
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints c;
		
		if (match.getWinner() != null)
		{
			JLabel winnerLabel = new JLabel("Winner:" + match.getWinner().getTeamName());
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 0;
			panel.add(winnerLabel, c);
		}
		
		//Label for HomeTeam
		JLabel HomeLabel = new JLabel("Home Team:");
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		panel.add(HomeLabel, c);
		
		JLabel homeScore = new JLabel();
		if (match.getHomeScore() != null)
			homeScore = new JLabel(Integer.toString(match.getHomeScore()));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		panel.add(homeScore, c);
		
		JLabel VSLabel = new JLabel("-VS-");
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		panel.add(VSLabel, c);
		
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyy");
		JLabel dateLabel = new JLabel(f.format(match.getTime()));
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		panel.add(dateLabel, c);
		
		JLabel awayLabel = new JLabel("Away Team:");
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		panel.add(awayLabel, c);
		
		JLabel awayScore = new JLabel();
		if (match.getAwayScore() != null)
			awayScore = new JLabel(Integer.toString(match.getAwayScore()));
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		panel.add(awayScore, c);
	}
	
	public void refreshLists()
	{
		
	}
}
