package com.sportstracker.entities;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.sportstracker.controller.DatabaseController;
import com.sportstracker.controller.TabController;

public class TeamTab extends CloseableTab
{
	JPanel panel;
	DatabaseController dbcontrol;
	
	private JTabbedPane tabbedPane;
	
	JTable upcomingTable;
	JTable pastTable;
	JTable playerTable;
	
	DefaultTableModel upcomingModel;
	DefaultTableModel pastModel;
	DefaultTableModel playerModel;
	
	private ListSelectionListener upcomingSelector;
	private ListSelectionListener pastSelector;
	private ListSelectionListener playerSelector;
	
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
				"id", "Opponent", "Location", "Date"
		}, 0);
		upcomingTable = new JTable(upcomingModel);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.5;
		upcomingTable.getColumn("id").setPreferredWidth(0);
		upcomingTable.getColumn("id").setWidth(0);
		upcomingTable.getColumn("id").setMinWidth(0);
		upcomingTable.getColumn("id").setMaxWidth(0);
		upcomingTable.getSelectionModel().removeListSelectionListener(upcomingSelector);
		panel.add(new JScrollPane(upcomingTable), c);
		
		// Past matches
		pastModel = new DefaultTableModel(new String[] {
				"id", "Opponent", "Location", "Score", "Result"
		}, 0);
		pastTable = new JTable(pastModel);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.5;
		pastTable.getColumn("id").setPreferredWidth(0);
		pastTable.getColumn("id").setWidth(0);
		pastTable.getColumn("id").setMinWidth(0);
		pastTable.getColumn("id").setMaxWidth(0);
		pastTable.getSelectionModel().removeListSelectionListener(pastSelector);
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
		playerTable.getSelectionModel().removeListSelectionListener(playerSelector);
		panel.add(new JScrollPane(playerTable), c);
		
		upcomingSelector = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
				{
					String matchName = (String)upcomingTable.getValueAt(upcomingTable.getSelectedRow(), 0);
					MatchTab info = new TabController().getNewMatchTab(matchName);
					info.addCloseTabListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// Remove the tab from the pane
							tabbedPane.remove(info);
							tabbedPane.setSelectedIndex(0);
						}
					});
				}
			}
		};
		upcomingSelector = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
				{
					String matchName = (String)upcomingTable.getValueAt(upcomingTable.getSelectedRow(), 0);
					MatchTab info = new TabController().getNewMatchTab(matchName);
					info.addCloseTabListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// Remove the tab from the pane
							tabbedPane.remove(info);
							tabbedPane.setSelectedIndex(0);
						}
					});
				}
			}
		};
		pastSelector = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
				{
					String matchName = (String)pastTable.getValueAt(pastTable.getSelectedRow(), 0);
					MatchTab info = new TabController().getNewMatchTab(matchName);
					info.addCloseTabListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// Remove the tab from the pane
							tabbedPane.remove(info);
							tabbedPane.setSelectedIndex(0);
						}
					});
				}
			}
		};
		
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
					"", "", "", ""
			});
		
		
		pastTable.setModel(pastModel);
		upcomingTable.setModel(upcomingModel);
		playerTable.setModel(playerModel);
	}
}
