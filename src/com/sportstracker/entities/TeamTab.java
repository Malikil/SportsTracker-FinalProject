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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.sportstracker.border.SportTrackerMain;
import com.sportstracker.controller.DatabaseController;
import com.sportstracker.controller.TabController;

import antlr.debug.ParserTokenListener;

public class TeamTab extends CloseableTab
{
	private JPanel panel;
	private DatabaseController dbcontrol;
	
	private SportTrackerMain owner;
	
	private JTable upcomingTable;
	private JTable pastTable;
	private JTable playerTable;
	
	private DefaultTableModel upcomingModel;
	private DefaultTableModel pastModel;
	private DefaultTableModel playerModel;
	
	private ListSelectionListener upcomingSelector;
	private ListSelectionListener pastSelector;
	private ListSelectionListener playerSelector;
	
	/**
	 * Create an instance of a closable team tab for the given team
	 * @param team The team to show details of
	 * @param owner The SportTrackerMain window. There's definitely a better
	 * way to do this but it's not immediately obvious to me in about 10
	 * seconds worth of thinking
	 */
	public TeamTab(SportTrackerMain owner, Team team)
	{
		super(team.getTeamName());
		dbcontrol = new DatabaseController();
		this.owner = owner;
		initialize(team);
		refreshLists();
		// Add the panel to the GridBagLayout
		addContent(panel);
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
		c.gridx = 0; c.gridy = 0;
		panel.add(rankLabel, c);
		
		// Win/loss
		JLabel winloss = new JLabel("Win/Loss: " + team.getWinCount() + "/" + team.getLossCount());
		c = new GridBagConstraints();
		c.gridx = 1; c.gridy = 0;
		panel.add(winloss, c);
		
		// Upcoming matches
		upcomingModel = new DefaultTableModel(new String[] {
				"id", "Opponent", "Location", "Date"
		}, 0);
		upcomingTable = new JTable(upcomingModel);
		c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5; c.weighty = 0.5;
		upcomingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//upcomingTable.getSelectionModel().removeListSelectionListener(upcomingSelector);
		panel.add(new JScrollPane(upcomingTable), c);
		
		// Past matches
		pastModel = new DefaultTableModel(new String[] {
				"id", "Opponent", "Location", "Score", "Result"
		}, 0);
		pastTable = new JTable(pastModel);
		c = new GridBagConstraints();
		c.gridx = 1; c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5; c.weighty = 0.5;
		pastTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//pastTable.getSelectionModel().removeListSelectionListener(pastSelector);
		panel.add(new JScrollPane(pastTable), c);
		
		// Player list
		playerModel = new DefaultTableModel(new String[] {
				"Player Name", "Jersy Number", "Position", "Minutes Played",
				"Games Played", "Penalty Attempts", "Penalty Scores"
		}, 0);
		playerTable = new JTable(playerModel);
		c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
		c.weightx = 1.0; c.weighty = 0.5;
		c.fill = GridBagConstraints.BOTH;
		playerTable.getSelectionModel().removeListSelectionListener(playerSelector);
		panel.add(new JScrollPane(playerTable), c);
		
		upcomingSelector = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
				{
					int matchid = Integer.parseInt((String)upcomingModel.getValueAt(upcomingTable.getSelectedRow(), 0));
					MatchTab info = new TabController(owner).getNewMatchTab(matchid);
					owner.addClosingTab("Match " + matchid, info);
				}
			}
		};
		pastSelector = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
				{
					int matchid = Integer.parseInt((String)pastModel.getValueAt(pastTable.getSelectedRow(), 0));
					MatchTab info = new TabController(owner).getNewMatchTab(matchid);
					owner.addClosingTab("Match " + matchid, info);
				}
			}
		};
		
	}
	
	/**
	 * Refresh the past and upcoming matches, and the player list
	 */
	public void refreshLists()
	{
		pastTable.getSelectionModel().removeListSelectionListener(pastSelector);
		upcomingTable.getSelectionModel().removeListSelectionListener(upcomingSelector);
		
		pastModel = new DefaultTableModel(new String[] {
				"id", "Opponent", "Location", "Score", "Result"
		}, 0);
		upcomingModel = new DefaultTableModel(new String[] {
				"id", "Opponent", "Location", "Date"
		}, 0);
		playerModel = new DefaultTableModel(new String[] {
				"Player Name", "Jersy Number", "Position", "Minutes Played",
				"Games Played", "Penalty Attempts", "Penalty Scores"
		}, 0);
		
		for (Match m : dbcontrol.getPastTeamMatches(getTabTitle()))
			pastModel.addRow(new String[] {
					Integer.toString(m.getId()),
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
					Integer.toString(m.getId()),
					m.getHomeTeam().getTeamName().equals(getTabTitle()) ?
							m.getAwayTeam().getTeamName() :
							m.getHomeTeam().getTeamName(),
					m.getHomeTeam().getTeamName(),
					date.format(m.getTime())
			});
		
		for (Player p : dbcontrol.getTeamPlayers(getTabTitle()))
			playerModel.addRow(new Object[] {
					p.getLastName() + ", " + p.getFirstName(),
					p.getJerseyNumber(),
					p.getPosition(),
					"", "", "", ""
			});
		
		
		pastTable.setModel(pastModel);
		TableColumnModel tcm = pastTable.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		pastTable.getSelectionModel().addListSelectionListener(pastSelector);
		upcomingTable.setModel(upcomingModel);
		tcm = upcomingTable.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
		upcomingTable.getSelectionModel().addListSelectionListener(upcomingSelector);
		playerTable.setModel(playerModel);
	}
}
