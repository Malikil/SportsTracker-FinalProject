package com.sportstracker.entities;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GameCard extends JPanel {
	/**
	 * Create the application.
	 */
	public GameCard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setPreferredSize(new Dimension(289, 170));
		setLayout(null);
		
		JLabel lblHomeTeam = new JLabel("Home Team");
		lblHomeTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHomeTeam.setBounds(21, 15, 92, 26);
		add(lblHomeTeam);
		
		JLabel lblAwayTeam = new JLabel("Away Team");
		lblAwayTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAwayTeam.setBounds(176, 15, 92, 26);
		add(lblAwayTeam);
		
		JLabel lblVs = new JLabel("V.S.");
		lblVs.setBounds(120, 75, 37, 26);
		add(lblVs);
		
		Panel homeImg = new Panel();
		homeImg.setBounds(50, 90, 10, 10);
		add(homeImg);
		
		Panel awayImg = new Panel();
		awayImg.setBounds(210, 90, 10, 10);
		add(awayImg);
		
		JLabel homeTeamLbl = new JLabel("");
		homeTeamLbl.setBounds(20, 50, 92, 26);
		add(homeTeamLbl);
		
		JLabel awayTeamLbl = new JLabel("");
		awayTeamLbl.setBounds(175, 50, 92, 26);
		add(awayTeamLbl);
		
		JLabel timeLbl = new JLabel("");
		timeLbl.setBounds(93, 110, 92, 26);
		add(timeLbl);
		
		JLabel locationLbl = new JLabel("");
		locationLbl.setBounds(93, 140, 92, 26);
		add(locationLbl);
		
		JLabel homeScoreLbl = new JLabel("0");
		homeScoreLbl.setBounds(42, 115, 30, 26);
		add(homeScoreLbl);
		
		JLabel awayScoreLbl = new JLabel("0");
		awayScoreLbl.setBounds(201, 115, 30, 26);
		add(awayScoreLbl);
	}
}
