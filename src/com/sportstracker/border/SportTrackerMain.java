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
import com.sportstracker.entities.Match;
import com.sportstracker.entities.MatchCard;
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
	private JTextField txtPlayerFName;
	private JTextField txtTeam;
	private JTextField txtPlayerLName;
	private JTextField txtPosition;
	private JTextField txtJerseyNumber;
	private JTextField txtAge;
	private JTextField txtWeight;
	private JTextField txtHeight;
	private JTextField homeTeamScoreText;
	private JTextField awayTeamScoreText;
	private JTextField matchTimeText;
	private ListSelectionListener teamSelector;
	private JPanel adminPanel;
	private JTabbedPane tabbedPane;
	
	// List/table models
	private DefaultComboBoxModel<String> homeTeamNameSelection;
	private DefaultComboBoxModel<String> awayTeamNameSelection;
	private DefaultTableModel teamsListModel;
	private DefaultListModel<String> unfollowedModel;
	private DefaultListModel<String> followedModel;
	// Lists/tables
	private JTable teamTable;
	private JComboBox<String> homeTeamNameText;
	private JComboBox<String> awayTeamNameText;
	private JList<String> unfollowed;
	private JList<String> followed;
	
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
		// Admin team list
		homeTeamNameSelection = new DefaultComboBoxModel<>();
		awayTeamNameSelection = new DefaultComboBoxModel<>();
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
		// TODO Update players
		
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
		// User
		this.followed.setModel(followedModel);
		unfollowed.setModel(unfollowedModel);
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
					TeamTab info = new TabController().getNewTeamTab(teamName);
					info.addCloseTabListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// Remove the tab from the pane
							tabbedPane.remove(info);
							tabbedPane.setSelectedIndex(0);
						}
					});
					tabbedPane.addTab(teamName, info);
					tabbedPane.setSelectedComponent(info);
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
				// TODO
			}
		});
		c = new GridBagConstraints();
		c.gridx = 1; c.gridy = 1; c.anchor = GridBagConstraints.PAGE_END; c.weighty = 0.5;
		userPanel.add(rightArrow, c);
		
		JButton leftArrow = new JButton("<--");
		c = new GridBagConstraints();
		c.gridx = 1; c.gridy = 2; c.gridheight= 2; c.anchor = GridBagConstraints.PAGE_START;c.weighty = 0.5;
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
		c = new GridBagConstraints();
		c.gridx = 3; c.gridy = 2; c.weightx = 0.3; c.weighty = 0.25;
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		userPanel.add(applyButton,c);
		
		JButton resetButton = new JButton("Reset Follow");
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
		
		JLabel lblMatches = new JLabel("Add a Match:");
		lblMatches.setBounds(484, 38, 73, 14);
		adminPanel.add(lblMatches);
		
		txtTeam = new JTextField();
		txtTeam.setBounds(302, 35, 86, 20);
		adminPanel.add(txtTeam);
		txtTeam.setColumns(10);
		
		homeTeamNameSelection = new DefaultComboBoxModel<>();
		awayTeamNameSelection = new DefaultComboBoxModel<>();
		
		homeTeamNameText = new JComboBox<>();
		homeTeamNameText.setBounds(553, 63, 86, 20);
		homeTeamNameText.setModel(homeTeamNameSelection);
		adminPanel.add(homeTeamNameText);
		
		JLabel lblHomeTeam = new JLabel("Home Team:");
		lblHomeTeam.setBounds(454, 66, 66, 14);
		adminPanel.add(lblHomeTeam);
		
		JLabel lblAwayTeam = new JLabel("Away Team:");
		lblAwayTeam.setBounds(454, 91, 66, 14);
		adminPanel.add(lblAwayTeam);
		
		awayTeamNameText = new JComboBox<>();
		awayTeamNameText.setBounds(553, 88, 86, 20);
		awayTeamNameText.setModel(awayTeamNameSelection);
		adminPanel.add(awayTeamNameText);
		
		JLabel lblHomeTeamScore = new JLabel("Home Team Score:");
		lblHomeTeamScore.setBounds(438, 122, 98, 14);
		adminPanel.add(lblHomeTeamScore);
		
		homeTeamScoreText = new JTextField();
		homeTeamScoreText.setBounds(553, 119, 86, 20);
		adminPanel.add(homeTeamScoreText);
		homeTeamScoreText.setColumns(10);
		
		JLabel lblAwayTeamScore = new JLabel("Away Team Score:");
		lblAwayTeamScore.setBounds(438, 153, 98, 14);
		adminPanel.add(lblAwayTeamScore);
		
		awayTeamScoreText = new JTextField();
		awayTeamScoreText.setBounds(553, 150, 86, 20);
		adminPanel.add(awayTeamScoreText);
		awayTeamScoreText.setColumns(10);
		
		JLabel lblTime = new JLabel("Date:");
		lblTime.setBounds(438, 219, 98, 14);
		adminPanel.add(lblTime);
		
		matchTimeText = new JTextField();
		matchTimeText.setBounds(553, 216, 86, 20);
		adminPanel.add(matchTimeText);
		matchTimeText.setColumns(10);
		
		JButton btnAddMatch = new JButton("Add Match");
		btnAddMatch.setBounds(484, 276, 119, 23);
		btnAddMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (adcont.addNewMatch(
						(String)homeTeamNameText.getSelectedItem(),
						(String)awayTeamNameText.getSelectedItem(),
						homeTeamScoreText.getText(),
						awayTeamScoreText.getText(),
						matchTimeText.getText()))
				{
					JOptionPane.showMessageDialog(null,
							"Match Added", "Create Match",
							JOptionPane.INFORMATION_MESSAGE);
					refreshLists();
				}
				else
					JOptionPane.showMessageDialog(null,
							"Match wasn't added", "Create Match",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		adminPanel.add(btnAddMatch);
		
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
		
		JButton btnAddPlayer = new JButton("Add Player");
		btnAddPlayer.setBounds(58, 318, 89, 23);
		btnAddPlayer.addActionListener(adcont.getPlayerListener());
		adminPanel.add(btnAddPlayer);
		
		// TODO add a combo box of players
		
		JButton btnUpdatePlayer = new JButton("Update Player");
		btnUpdatePlayer.addActionListener(adcont.getPlayerListener());
		// TODO add
	}
}
