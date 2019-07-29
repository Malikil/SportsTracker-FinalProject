package com.sportstracker.border;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.sportstracker.controller.AdminController;
import com.sportstracker.controller.DatabaseController;
import com.sportstracker.controller.LoginManager;
import com.sportstracker.controller.MatchManager;
import com.sportstracker.controller.TabController;
import com.sportstracker.entities.CloseableTab;
import com.sportstracker.entities.Match;
import com.sportstracker.entities.MatchCard;
import com.sportstracker.entities.Player;
import com.sportstracker.entities.Team;
import com.sportstracker.entities.TeamTab;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JCheckBox;

/**
 * The main window the user will be interacting with
 */
public class SportTrackerMain
{
	private JFrame frame;
	private String currentUser;
	private JTextField txtSearch;
	private JTable table_1;
	private JTextField txtTeam;
	private ListSelectionListener teamSelector;
	private JPanel adminPanel;
	private JTabbedPane tabbedPane;
	
	// List/table models
	private DefaultComboBoxModel<String> homeTeamNameSelection;
	private DefaultComboBoxModel<String> awayTeamNameSelection;
	private DefaultTableModel teamsListModel;
	private DefaultListModel<String> unfollowedModel;
	private DefaultListModel<String> followedModel;
	private DefaultComboBoxModel<String> playerNamesModel;
	
	// Lists/tables
	private JTable teamTable;
	private JComboBox<String> homeTeamNameText;
	private JComboBox<String> awayTeamNameText;
	private JList<String> unfollowed;
	private JList<String> followed;
	private JComboBox<String> playerNamesList;
	
	// Flow layout panel for upcoming games
	private JPanel gameSchedulePanel;
	private JPanel recentGamesPanel;
	private JPanel followedGamesPanel;
	
	/**
	 * Sets visibility of the form
	 * @param visible Whether the form should be visible
	 */
	public void setVisible(boolean visible)
	{
		frame.setVisible(visible);
	}

	/**
	 * Create the application.
	 * @wbp.parser.constructor
	 */
	public SportTrackerMain(String cUser) { this(cUser, false); }
	public SportTrackerMain(String cUser, boolean isAdmin)
	{
		currentUser = cUser;
		initialize();
		
		if (isAdmin)
			tabbedPane.addTab("Admin", adminPanel);
		
		refreshLists();
	}
	
	/**
	 * Refresh the JTables for teams and players, and update the team
	 * dropdowns on the admin tab
	 */
	public void refreshLists()
	{
		// Set up new variables
		DatabaseController dbcon = new DatabaseController();
		MatchManager mm = new MatchManager();
		// Home page
		gameSchedulePanel.removeAll();
		recentGamesPanel.removeAll();
		followedGamesPanel.removeAll();
		// Admin lists
		homeTeamNameSelection = new DefaultComboBoxModel<>();
		awayTeamNameSelection = new DefaultComboBoxModel<>();
		playerNamesModel = new DefaultComboBoxModel<>();
		// Special list tabs
		teamTable.getSelectionModel().removeListSelectionListener(teamSelector);
		teamsListModel = new DefaultTableModel(new Object[] {
				"Team name", "Wins", "Losses"
		}, 0);
		// User page lists
		followedModel = new DefaultListModel<>();
		unfollowedModel = new DefaultListModel<>();
		
		// Load followed and unfollowed teams for userpage and load
		// teams into admin lists
		List<String> followed = dbcon.getFollowedTeams(currentUser);
		for (Team t : dbcon.getAllTeams())
		{
			homeTeamNameSelection.addElement(t.getTeamName());
			awayTeamNameSelection.addElement(t.getTeamName());
			teamsListModel.addRow(new Object[] {
					t.getTeamName(),
					t.getWinCount(),
					t.getLossCount()
			});
			
			if (followed.contains(t.getTeamName()))
				followedModel.addElement(t.getTeamName());
			else
				unfollowedModel.addElement(t.getTeamName());
		}
		
		// Load players into admin list
		for (Player p : dbcon.getAllPlayers())
			playerNamesModel.addElement(p.getFirstName() + " " + p.getLastName());
		
		// Load matches into home page
		List<Match> upcoming = mm.getUpcomingMatches();
		for (int i = 0; i < 10 && i < upcoming.size(); i++)
		{
			MatchCard card = new MatchCard(upcoming.get(i));
			card.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = i;
			c.gridy = 0;
			c.fill = GridBagConstraints.VERTICAL;
			c.insets = new Insets(10, 10, 10, 10);
			gameSchedulePanel.add(card, c);
		}
		List<Match> past = mm.getPastMatches();
		for (int i = 0; i < 10 && i < past.size(); i++)
		{
			MatchCard card = new MatchCard(past.get(i));
			card.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = i;
			c.gridy = 0;
			c.fill = GridBagConstraints.VERTICAL;
			c.insets = new Insets(5, 5, 5, 5);
			recentGamesPanel.add(card, c);
		}
		// Followed teams
		List<Match> followpast = dbcon.getPastMatchesForTeamList(followed);
		for (int i = 0; i < followpast.size(); i++)
		{
			MatchCard card = new MatchCard(followpast.get(i));
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = i;
			c.gridy = 0;
			c.fill = GridBagConstraints.VERTICAL;
			c.insets = new Insets(5, 5, 5, 5);
			followedGamesPanel.add(card, c);
		}
		List<Match> followup = dbcon.getUpcomingMatchesForTeamList(followed);
		for (int i = 0; i < followpast.size(); i++)
		{
			MatchCard card = new MatchCard(followup.get(i));
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = i;
			c.gridy = 0;
			c.fill = GridBagConstraints.VERTICAL;
			c.insets = new Insets(5, 5, 5, 5);
			followedGamesPanel.add(card, c);
		}
		
		// Commit to new values
		teamTable.setModel(teamsListModel);
		teamTable.getSelectionModel().addListSelectionListener(teamSelector);
		// Admin
		homeTeamNameText.setModel(homeTeamNameSelection);
		awayTeamNameText.setModel(awayTeamNameSelection);
		playerNamesList.setModel(playerNamesModel);
		// User
		this.followed.setModel(followedModel);
		unfollowed.setModel(unfollowedModel);
	}
	
	public void addClosingTab(String name, CloseableTab tab)
	{
		tab.addCloseTabListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Remove the tab from the pane
				tabbedPane.remove(tab);
				tabbedPane.setSelectedIndex(0);
			}
		});
		tabbedPane.addTab(name, tab);
		tabbedPane.setSelectedComponent(tab);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		JPanel homePanel = new JPanel();
		tabbedPane.addTab("Home", null, homePanel, null);
		homePanel.setLayout(new GridBagLayout());
		
		JPanel teamPanel = new JPanel();
		tabbedPane.addTab("Team", null, teamPanel, null);
		teamPanel.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(570, 11, 86, 20);
		teamPanel.add(txtSearch);
		txtSearch.setColumns(10);
		
		teamSelector = new ListSelectionListener() {	
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
				{
					String teamName = (String)teamTable.getValueAt(teamTable.getSelectedRow(), 0);
					TeamTab info = new TabController(SportTrackerMain.this).getNewTeamTab(teamName);
					addClosingTab(teamName, info);
				}
			}
		};
		teamsListModel = new DefaultTableModel(new Object[] {
				"Team name", "Wins", "Losses"
		}, 0);
		teamTable = new JTable(teamsListModel);
		teamTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		teamTable.getSelectionModel().addListSelectionListener(teamSelector);
		JScrollPane tablePane = new JScrollPane(teamTable);
		tablePane.setBounds(10, 42, 679, 360);
		teamPanel.add(tablePane);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE; c.gridy = 0;
		c.gridx = 0; c.weightx = 1.0;
		c.insets = new Insets(5, 10, 0, 10);
		homePanel.add(new JLabel("Upcoming Games"), c);
		
		gameSchedulePanel = new JPanel();
		gameSchedulePanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridy = 1; c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.33; c.gridx = 0; c.weightx = 1.0;
		c.insets = new Insets(0, 5, 3, 5);
		homePanel.add(new JScrollPane(gameSchedulePanel), c);
		
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE; c.gridy = 2;
		c.gridx = 0; c.weightx = 1.0;
		c.insets = new Insets(3, 10, 0, 10);
		homePanel.add(new JLabel("Recent Games"), c);
		
		recentGamesPanel = new JPanel();
		recentGamesPanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.insets = new Insets(0, 5, 3, 5);
		c.gridy = 3; c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.33; c.gridx = 0; c.weightx = 1.0;
		homePanel.add(new JScrollPane(recentGamesPanel), c);
		
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE; c.gridy = 4;
		c.gridx = 0; c.weightx = 1.0;
		c.insets = new Insets(3, 10, 0, 10);
		homePanel.add(new JLabel("Followed Games"), c);
		
		followedGamesPanel = new JPanel();
		followedGamesPanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridy = 5; c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.33; c.gridx = 0; c.weightx = 1.0;
		c.insets = new Insets(0, 5, 5, 5);
		homePanel.add(new JScrollPane(followedGamesPanel), c);
		
		JPanel playerPanel = new JPanel();
		tabbedPane.addTab("Player", null, playerPanel, null);
		playerPanel.setLayout(new BorderLayout(0, 0));
		
		table_1 = new JTable();
		playerPanel.add(table_1, BorderLayout.CENTER);
		
		// ============================== User Settings Panel ==============================
		JPanel userPanel = new JPanel();
		userPanel.setLayout(new GridBagLayout());
		tabbedPane.addTab("Settings", userPanel);
		
		// Change followed teams
		JLabel unfollowedLabel = new JLabel("Add teams to followed");
		c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 0; c.weightx = 0.33;
		userPanel.add(unfollowedLabel, c);
		
		unfollowedModel = new DefaultListModel<>();
		unfollowed = new JList<>(unfollowedModel);
		c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 1; c.weightx = 0.33; c.weighty = 1.0;
		c.gridheight = 3; c.fill = GridBagConstraints.BOTH;
		userPanel.add(new JScrollPane(unfollowed), c);
		
		JButton rightArrow = new JButton("-->");
		rightArrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// If a team is selected in the left list,
				// move it to the right list
				int[] selected = unfollowed.getSelectedIndices();
				for (int i = 0; i < selected.length; i++)
				{
					// Add to right list
					followedModel.addElement(unfollowedModel.get(selected[i] - i));
					// Remove from left list
					unfollowedModel.remove(selected[i] - i);
				}
			}
		});
		c = new GridBagConstraints();
		c.gridx = 1; c.gridy = 1; c.anchor = GridBagConstraints.PAGE_END; c.weighty = 0.5;
		userPanel.add(rightArrow, c);
		
		JButton leftArrow = new JButton("<--");
		leftArrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// If a team is selected in the right list,
				// move it to the left list
				int[] selected = followed.getSelectedIndices();
				for (int i = 0; i < selected.length; i++)
				{
					// Add to right list
					unfollowedModel.addElement(followedModel.get(selected[i] - i));
					// Remove from left list
					followedModel.remove(selected[i] - i);
				}
			}
		});
		c = new GridBagConstraints();
		c.gridx = 1; c.gridy = 2; c.gridheight= 2;
		c.anchor = GridBagConstraints.PAGE_START;c.weighty = 0.5;
		userPanel.add(leftArrow, c);
		
		
		JLabel followedLabel = new JLabel("Followed teams");
		c = new GridBagConstraints();
		c.gridx = 2; c.gridy = 0; c.weightx = 0.3;
		userPanel.add(followedLabel, c);
		
		followedModel = new DefaultListModel<>();
		followed = new JList<>(followedModel);
		c = new GridBagConstraints();
		c.gridx = 2; c.gridy = 1; c.weightx = 0.3; c.weighty = 1.0;
		c.gridheight = 3; c.fill = GridBagConstraints.BOTH;
		userPanel.add(new JScrollPane(followed), c);
		
		// User passwords
		
		JButton changePassButton = new JButton("Change Password");
		changePassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChangePasswordPanel newpw = new ChangePasswordPanel();
				String[] buttons = { "Okay", "Cancel" };
				if (JOptionPane.showOptionDialog(null,
						newpw.getFrame(), "Change Password",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null,
						buttons, buttons[0]) == 0)
					if (new LoginManager().changePassword(
							currentUser,
							newpw.getOldPassword(),
							newpw.getOldPassword()))
						JOptionPane.showMessageDialog(null,
								"Password Updated", "Change Password",
								JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null,
								"Password was not updated", "Change Password",
								JOptionPane.WARNING_MESSAGE);
			}
		});
		c= new GridBagConstraints();
		c.gridx = 3 ; c.gridy= 1; c.weightx = 0.3; c.weighty = 0.5;
		c.anchor= GridBagConstraints.LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		userPanel.add(changePassButton,c);
		
		JButton applyButton = new JButton("Apply Follow");
		applyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> teamlist = new ArrayList<>();
				for (Object team : followedModel.toArray())
					teamlist.add((String)team);
				new LoginManager().updateFavourites(currentUser, teamlist);
				refreshLists();
			}
		});
		c = new GridBagConstraints();
		c.gridx = 3; c.gridy = 2; c.weightx = 0.3; c.weighty = 0.25;
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		userPanel.add(applyButton,c);
		
		JButton resetButton = new JButton("Reset Follow");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshLists();
				// Because I'm lazy uwu
			}
		});
		c = new GridBagConstraints();
		c.gridx = 3; c.gridy = 3; c.weightx = 0.3; c.weighty = 0.25;
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		userPanel.add(resetButton,c);
		 
		
		
		// =========================== Admin Panel ===========================
		AdminController adcont = new AdminController(); 
		adminPanel = new JPanel();
		adminPanel.setLayout(null);
		
		JLabel lblTeam = new JLabel("Add a Team:");
		lblTeam.setBounds(231, 38, 61, 14);
		adminPanel.add(lblTeam);
		
		txtTeam = new JTextField();
		txtTeam.setBounds(302, 35, 86, 20);
		adminPanel.add(txtTeam);
		txtTeam.setColumns(10);
		
		// ========== Matches ==========
		homeTeamNameText = new JComboBox<>();
		homeTeamNameText.setBounds(484, 250, 119, 23);
		adminPanel.add(homeTeamNameText);
		
		awayTeamNameText = new JComboBox<>();
		awayTeamNameText.setBounds(484, 225, 119, 23);
		adminPanel.add(awayTeamNameText);
		
		JTextField dateSelectBox = new JTextField();
		dateSelectBox.setBounds(484, 200, 119, 23);
		adminPanel.add(dateSelectBox);
		
		JButton updateMatchButton = new JButton("Update Match");
		updateMatchButton.setBounds(484, 175, 119, 23);
		updateMatchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean updated = adcont.updateMatch(
						(String)homeTeamNameText.getSelectedItem(),
						(String)awayTeamNameText.getSelectedItem(),
						dateSelectBox.getText());
				if (updated != null)
				{
					if (updated)
						JOptionPane.showMessageDialog(null,
								"Match updated successfully", "Update Match",
								JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null,
								"Match wasn't updated", "Update Match",
								JOptionPane.WARNING_MESSAGE);
					refreshLists();
				}
			}
		});
		adminPanel.add(updateMatchButton);
		
		JButton btnAddMatch = new JButton("Add Match");
		btnAddMatch.setBounds(484, 276, 119, 23);
		btnAddMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean created = adcont.createNewMatch();
				if (created != null)
				{
					if (created)
						JOptionPane.showMessageDialog(null,
								"Match Added", "Create Match",
								JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null,
								"Match wasn't added", "Create Match",
								JOptionPane.WARNING_MESSAGE);
					refreshLists();
				}
			}
		});
		adminPanel.add(btnAddMatch);
		
		// ========== Teams ==========
		JButton btnAddTeam = new JButton("Add Team");
		btnAddTeam.setBounds(266, 118, 107, 23);
		btnAddTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Collect the player information and use the controller to add it
				if (adcont.addNewTeam(txtTeam.getText()))
				{
					JOptionPane.showMessageDialog(null,
							"Team Created", "Create Team",
							JOptionPane.INFORMATION_MESSAGE);
					refreshLists();
				}
				else
					JOptionPane.showMessageDialog(null,
							"Team wasn't created", "Create Team",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		adminPanel.add(btnAddTeam);
		
		// ========== Players ==========
		JButton btnAddPlayer = new JButton("Add Player");
		btnAddPlayer.setBounds(58, 318, 89, 23);
		btnAddPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean created = adcont.createNewPlayer();
				if (created != null)
				{
					if (created)
						JOptionPane.showMessageDialog(null, "Player created successfully");
					else
						JOptionPane.showMessageDialog(null, "Player was not created");
					refreshLists();
				}
			}
		});
		adminPanel.add(btnAddPlayer);
		
		playerNamesList = new JComboBox<>();
		playerNamesList.setBounds(58, 200, 100, 25);
		adminPanel.add(playerNamesList);
		
		JButton btnUpdatePlayer = new JButton("Update Player");
		btnUpdatePlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean created = adcont.updatePlayer((String)playerNamesList.getSelectedItem());
				if (created != null)
				{
					if (created)
						JOptionPane.showMessageDialog(null, "Player updated successfully");
					else
						JOptionPane.showMessageDialog(null, "Player was not updated");
					refreshLists();
				}
			}
		});
		btnUpdatePlayer.setBounds(58, 250, 89, 23);
		adminPanel.add(btnUpdatePlayer);
	}
}
