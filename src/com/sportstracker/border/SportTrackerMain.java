package com.sportstracker.border;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;

public class SportTrackerMain {

	private JFrame frame;
	
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 879, 741);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 843, 680);
		frame.getContentPane().add(tabbedPane);
		
		JPanel homePanel = new JPanel();
		tabbedPane.addTab("Home", null, homePanel, null);
		homePanel.setLayout(null);
		
		JPanel gameSchedulePanel = new JPanel();
		gameSchedulePanel.setBounds(10, 11, 818, 180);
		homePanel.add(gameSchedulePanel);
		
		JPanel resentGamesPanel = new JPanel();
		resentGamesPanel.setBounds(10, 225, 818, 180);
		homePanel.add(resentGamesPanel);
		
		JPanel FollowedGamesPanel = new JPanel();
		FollowedGamesPanel.setBounds(10, 441, 818, 180);
		homePanel.add(FollowedGamesPanel);
		
		JPanel teamPanel = new JPanel();
		tabbedPane.addTab("Team", null, teamPanel, null);
		teamPanel.setLayout(null);
		
		JPanel Standing = new JPanel();
		Standing.setBounds(26, 11, 787, 291);
		teamPanel.add(Standing);
	}
}
