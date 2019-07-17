package com.sportstracker.entities;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sportstracker.controller.DatabaseController;

public class TeamTab extends CloseableTab
{
	JPanel panel;
	DatabaseController dbcontrol;
	
	JTable upcomingTable;
	JTable pastTable;
	JTable playerTable;
	
	DefaultTableModel upcomingModel;
	DefaultTableModel pastModel;
	DefaultTableModel playerModel;
	
	/**
	 * Create an instance of a closable team tab for the given team
	 * @param team
	 */
	public TeamTab(Team team)
	{
		super(team.getTeamName());
		dbcontrol = new DatabaseController();
		initialize(team);
		refreshLists();
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
		JLabel winloss = new JLabel("Win/Loss: " + team.getWinCount() + "/" + team.getLossCount());
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		panel.add(winloss, c);
		
		// Upcoming matches
		upcomingModel = new DefaultTableModel(new String[] {
				"Opponent", "Location", "Date"
		}, 0);
		upcomingTable = new JTable(upcomingModel);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.5;
		panel.add(new JScrollPane(upcomingTable), c);
		
		// Past matches
		pastModel = new DefaultTableModel(new String[] {
				"Opponent", "Location", "Score", "Result"
		}, 0);
		pastTable = new JTable(pastModel);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.5;
		panel.add(new JScrollPane(pastTable), c);
		
		// Player list
		playerModel = new DefaultTableModel(new String[] {
				"Player Name", "Jersy Number", "Position", "Minutes Played",
				"Games Played", "Penalty Attempts", "Penalty Scores"
		}, 0);
		playerTable = new JTable(playerModel);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.weightx = 1.0;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.BOTH;
		panel.add(new JScrollPane(playerTable), c);
	}
	
	/**
	 * Refresh the past and upcoming matches, and the player list
	 */
	public void refreshLists()
	{
		pastModel = new DefaultTableModel(new String[] {
				"Opponent", "Location", "Score", "Result"
		}, 0);
		upcomingModel = new DefaultTableModel(new String[] {
				"Opponent", "Location", "Date"
		}, 0);
		playerModel = new DefaultTableModel(new String[] {
				"Player Name", "Jersy Number", "Position", "Minutes Played",
				"Games Played", "Penalty Attempts", "Penalty Scores"
		}, 0);
		
		for (Match m : dbcontrol.getPastTeamMatches(getTabTitle()))
			pastModel.addRow(new String[] {
					m.getHomeTeam().getTeamName().equals(getTabTitle()) ?
							m.getAwayTeam().getTeamName() :
							m.getHomeTeam().getTeamName(),
					m.getHomeTeam().getTeamName(),
					m.getHomeScore() + " - " + m.getAwayScore(),
					m.getWinner() == null ? "Tied" :
						m.getWinner().getTeamName().equals(getTabTitle()) ?
							"Won" : "Lost"
			});
		
		SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
		for (Match m : dbcontrol.getUpcomingTeamMatches(getTabTitle()))
			upcomingModel.addRow(new String[] {
					m.getHomeTeam().getTeamName().equals(getTabTitle()) ?
							m.getAwayTeam().getTeamName() :
							m.getHomeTeam().getTeamName(),
					m.getHomeTeam().getTeamName(),
					date.format(m.getTime())
			});
		
		for (Player p : dbcontrol.getTeamPlayers(getTabTitle()))
			playerModel.addRow(new Object[] {
					p.getPLastName() + ", " + p.getPFirstName(),
					p.getJerseyNumber(),
					p.getPosition(),
					p.getMinutesPlayed(),
					p.getGamesPlayed(),
					p.getAttemptedPenalties(),
					p.getScoredPenalties()
			});
		
		pastTable.setModel(pastModel);
		upcomingTable.setModel(upcomingModel);
		playerTable.setModel(playerModel);
	}
}
