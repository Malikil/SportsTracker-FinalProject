package com.sportstracker.border;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JTextField;

import com.sportstracker.controller.AdminController;
import com.sportstracker.controller.MatchManager;
import com.sportstracker.entities.Match;
import com.sportstracker.entities.MatchCard;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The main window the user will be interacting with
 */
public class SportTrackerMain
{
	private JFrame frame;
	private JTextField txtSearch;
	private JTable table;
	private JTable table_1;
	private JTextField txtPlayerFName;
	private JTextField txtTeam;
	private JTextField homeTeamNameText;
	private JTextField txtPlayLName;
	private JTextField txtPosition;
	private JTextField txtJerseyNumber;
	private JTextField txtAge;
	private JTextField txtWeight;
	private JTextField textField;
	private JTextField awayTeamNameText;
	private JTextField homeTeamScoreText;
	private JTextField awayTeamScoreText;
	private JTextField txtLocation;
	private JTextField matchTimeText;
	
	// Flow layout panel for upcoming games
	JPanel gameSchedulePanel;
	JPanel recentGamesPanel;
	
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
	public SportTrackerMain() { this(false); }
	public SportTrackerMain(boolean isAdmin)
	{
		initialize();
		MatchManager mm = new MatchManager();
		List<Match> upcoming = mm.getUpcomingMatches();
		for (int i = 0; i < 10 && i < upcoming.size(); i++)
			gameSchedulePanel.add(new MatchCard(upcoming.get(i)));
		List<Match> past = mm.getPastMatches();
		for (int i = 0; i < 10 && i < past.size(); i++)
			recentGamesPanel.add(new MatchCard(past.get(i)));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		JPanel homePanel = new JPanel();
		tabbedPane.addTab("Home", null, homePanel, null);
		homePanel.setLayout(new GridLayout(3, 1, 10, 10));
		
		JPanel teamPanel = new JPanel();
		tabbedPane.addTab("Team", null, teamPanel, null);
		teamPanel.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(570, 11, 86, 20);
		teamPanel.add(txtSearch);
		txtSearch.setColumns(10);
		
		table = new JTable();
		table.setBounds(10, 42, 679, 360);
		teamPanel.add(table);
		
		gameSchedulePanel = new JPanel();
		homePanel.add(gameSchedulePanel);
		gameSchedulePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		recentGamesPanel = new JPanel();
		homePanel.add(recentGamesPanel);
		
		JPanel followedGamesPanel = new JPanel();
		homePanel.add(followedGamesPanel);
		
		JPanel playerPanel = new JPanel();
		tabbedPane.addTab("Player", null, playerPanel, null);
		playerPanel.setLayout(new BorderLayout(0, 0));
		
		table_1 = new JTable();
		playerPanel.add(table_1, BorderLayout.CENTER);
		
		JPanel MatchesPanel = new JPanel();
		tabbedPane.addTab("Matches", null, MatchesPanel, null);
		
		JPanel adminPanel = new JPanel();
		tabbedPane.addTab("Admin", null, adminPanel, null);
		adminPanel.setLayout(null);
		
		JLabel lblPlayer = new JLabel("Add a Player to a Team:");
		lblPlayer.setBounds(22, 38, 73, 14);
		adminPanel.add(lblPlayer);
		
		JLabel lblTeam = new JLabel("Add a Team:");
		lblTeam.setBounds(231, 38, 61, 14);
		adminPanel.add(lblTeam);
		
		JLabel lblMatches = new JLabel("Add a Match:");
		lblMatches.setBounds(484, 38, 73, 14);
		adminPanel.add(lblMatches);
		
		txtPlayerFName = new JTextField();
		txtPlayerFName.setBounds(96, 60, 86, 20);
		adminPanel.add(txtPlayerFName);
		txtPlayerFName.setColumns(10);
		
		txtTeam = new JTextField();
		txtTeam.setBounds(302, 35, 86, 20);
		adminPanel.add(txtTeam);
		txtTeam.setColumns(10);
		
		homeTeamNameText = new JTextField();
		homeTeamNameText.setBounds(553, 63, 86, 20);
		adminPanel.add(homeTeamNameText);
		homeTeamNameText.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(29, 63, 66, 14);
		adminPanel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(29, 88, 61, 14);
		adminPanel.add(lblLastName);
		
		txtPlayLName = new JTextField();
		txtPlayLName.setBounds(96, 88, 86, 20);
		adminPanel.add(txtPlayLName);
		txtPlayLName.setColumns(10);
		
		txtPosition = new JTextField();
		txtPosition.setBounds(96, 119, 86, 20);
		adminPanel.add(txtPosition);
		txtPosition.setColumns(10);
		
		txtJerseyNumber = new JTextField();
		txtJerseyNumber.setBounds(96, 150, 86, 20);
		adminPanel.add(txtJerseyNumber);
		txtJerseyNumber.setColumns(10);
		
		txtAge = new JTextField();
		txtAge.setBounds(96, 181, 86, 20);
		adminPanel.add(txtAge);
		txtAge.setColumns(10);
		
		txtWeight = new JTextField();
		txtWeight.setBounds(96, 216, 86, 20);
		adminPanel.add(txtWeight);
		txtWeight.setColumns(10);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(39, 122, 46, 14);
		adminPanel.add(lblPosition);
		
		JLabel lblJerseyNumber = new JLabel("Jersey Number:");
		lblJerseyNumber.setBounds(9, 153, 86, 14);
		adminPanel.add(lblJerseyNumber);
		
		JLabel lblAge = new JLabel("Players Age:");
		lblAge.setBounds(22, 184, 66, 14);
		adminPanel.add(lblAge);
		
		textField = new JTextField();
		textField.setBounds(96, 247, 86, 20);
		adminPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblWeight = new JLabel("Players Weight:");
		lblWeight.setBounds(9, 219, 86, 14);
		adminPanel.add(lblWeight);
		
		JLabel lblHeight = new JLabel("Players Height:");
		lblHeight.setBounds(9, 250, 86, 14);
		adminPanel.add(lblHeight);
		
		JComboBox comboBoxAddPlayer = new JComboBox();
		comboBoxAddPlayer.setBounds(107, 35, 75, 20);
		adminPanel.add(comboBoxAddPlayer);
		
		JLabel lblHomeTeam = new JLabel("Home Team:");
		lblHomeTeam.setBounds(454, 66, 66, 14);
		adminPanel.add(lblHomeTeam);
		
		JLabel lblAwayTeam = new JLabel("Away Team:");
		lblAwayTeam.setBounds(454, 91, 66, 14);
		adminPanel.add(lblAwayTeam);
		
		awayTeamNameText = new JTextField();
		awayTeamNameText.setBounds(553, 88, 86, 20);
		adminPanel.add(awayTeamNameText);
		awayTeamNameText.setColumns(10);
		
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
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setBounds(474, 184, 46, 14);
		adminPanel.add(lblLocation);
		
		txtLocation = new JTextField();
		txtLocation.setBounds(553, 181, 86, 20);
		adminPanel.add(txtLocation);
		txtLocation.setColumns(10);
		
		JLabel lblTime = new JLabel("Time of the Game:");
		lblTime.setBounds(438, 219, 98, 14);
		adminPanel.add(lblTime);
		
		matchTimeText = new JTextField();
		matchTimeText.setBounds(553, 216, 86, 20);
		adminPanel.add(matchTimeText);
		matchTimeText.setColumns(10);
		
		JButton btnAddMatch = new JButton("Add Match");
		btnAddMatch.setBounds(484, 276, 119, 23);
		adminPanel.add(btnAddMatch);
		
		JButton btnAddTeam = new JButton("Add Team");
		btnAddTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Collect the player information and use the controller to add it
				if (new AdminController().addNewTeam(txtTeam.getText()))
					JOptionPane.showMessageDialog(null,
							"Team Created", "Create Team",
							JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null,
							"Team wasn't created", "Create Team",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		btnAddTeam.setBounds(266, 118, 107, 23);
		adminPanel.add(btnAddTeam);
		
		JButton btnAddPlayer = new JButton("Add Team");
		btnAddPlayer.setBounds(57, 297, 89, 23);
		adminPanel.add(btnAddPlayer);
	}
}
