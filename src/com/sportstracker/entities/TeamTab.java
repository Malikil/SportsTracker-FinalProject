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
		c.fill = GridBagConstraints.BOTH;
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
		
		// Win/loss
		JLabel winloss = new JLabel("Win/Loss: " + team.getWinCount() + "/" + (team.getMatchCount() - team.getWinCount()));
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		panel.add(winloss, c);
		
		// Upcoming matches
		DefaultTableModel upcomingModel = new DefaultTableModel(new String[] {
				"Opponent", "Location", "Date"
		}, 0);
		JTable upcomingTable = new JTable(upcomingModel);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.5;
		panel.add(upcomingTable, c);
		
		// Past matches
		DefaultTableModel pastModel = new DefaultTableModel(new String[] {
				"Opponent", "Location", "Score", "Result"
		}, 0);
		JTable pastTable = new JTable(pastModel);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.5;
		panel.add(pastTable, c);
		
		// Player list
		DefaultTableModel model = new DefaultTableModel(new String[] {
				"Player Name", "Jersy Number", "Position", "Minutes Played",
				"Games Played", "Penalty Attempts", "Penalty Scores"
		}, 0);
		for (Player p : team.getPlayers())
			model.addRow(new Object[] {
					p.getPLastName() + ", " + p.getPFirstName(),
					p.getJerseyNumber(),
					p.getPosition(),
					p.getMinutesPlayed(),
					p.getGamesPlayed(),
					p.getAttemptedPenalties(),
					p.getScoredPenalties()
			});
		JTable table = new JTable(model);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.weightx = 1.0;
		c.weighty = 0.5;
		panel.add(table, c);
	}
	
	private void updateLists()
	{
		
	}
}
