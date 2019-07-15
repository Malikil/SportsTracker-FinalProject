package com.sportstracker.entities;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class TeamTab extends CloseableTab
{
	JPanel panel;
	
	/**
	 * Create an instance of a closable team tab for the given team
	 * @param team
	 */
	public TeamTab(Team team)
	{
		super(team.getTeamName());
		initialize(team);
		// Add the panel to the GridBagLayout
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 2;
		add(panel, c);
	}
	
	public void initialize(Team team)
	{
		panel = new JPanel();
		panel.setLayout(new GridLayout());
	}
}
