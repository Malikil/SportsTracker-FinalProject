package com.sportstracker.entities;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sportstracker.controller.DatabaseController;

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
	
	/**
	 * Initialize the contents of the frame
	 * @param team The team to grab information from
	 */
	public void initialize(Team team)
	{
		DatabaseController dbcontrol = new DatabaseController();
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints c;
		// Team rank
		JLabel rankLabel = new JLabel("Rank: #" + dbcontrol.getTeamRank(team));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		panel.add(rankLabel, c);
		
		// Dud column to fill space with
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		panel.add(null, c);
		
		// Win/loss
		JLabel winloss = new JLabel("Win/Loss: " + team.getWinCount() + "/" + (team.getMatchCount() - team.getWinCount()));
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		panel.add(winloss, c);
		
		// Player list
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Player Name");
		model.addColumn("Jersy Number");
		model.addColumn("Position");
		model.addColumn("Minutes Played");
		model.addColumn("Games Played");
		model.addColumn("Penalty Attempts");
		model.addColumn("Penalty Scores");
		JTable table = new JTable(model);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		//c.weightx = 1.0;
		c.weighty = 1.0;
		panel.add(table, c);
	}
}
