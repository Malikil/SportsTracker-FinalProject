package com.sportstracker.entities;

import java.awt.*;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MatchCard extends JPanel
{
	private JLabel lblHomeTeam;
	private JLabel lblAwayTeam;
	private Panel homeImg;
	private Panel awayImg;
	private JLabel homeTeamLbl;
	private JLabel awayTeamLbl;
	private JLabel timeLbl;
	private JLabel locationLbl;
	private JLabel homeScoreLbl;
	private JLabel awayScoreLbl;
	
	/**
	 * Create the GameCard.
	 */
	public MatchCard(Match nMatch) {
		
		initialize();
		
		lblHomeTeam.setText(nMatch.getHomeTeam().getTeamName());
		lblAwayTeam.setText(nMatch.getAwayTeam().getTeamName());
		if (new Date().after(nMatch.getTime()))
		{
			// If the match hasn't happened yet, don't show scores
			homeScoreLbl.setText(Integer.toString(nMatch.getHomeScore()));
			awayScoreLbl.setText(Integer.toString(nMatch.getAwayScore()));
		}
		//locationLbl.setText(nMatch.getLocation());
		locationLbl.setText(nMatch.getTime().toString());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setPreferredSize(new Dimension(289, 170));
		setLayout(null);
		
		lblHomeTeam = new JLabel("Home Team");
		lblHomeTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHomeTeam.setBounds(21, 15, 92, 26);
		add(lblHomeTeam);
		
		lblAwayTeam = new JLabel("Away Team");
		lblAwayTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAwayTeam.setBounds(176, 15, 92, 26);
		add(lblAwayTeam);
		
		JLabel lblVs = new JLabel("V.S.");
		lblVs.setBounds(120, 75, 37, 26);
		add(lblVs);
		
		homeImg = new Panel();
		homeImg.setBounds(50, 90, 10, 10);
		add(homeImg);
		
		awayImg = new Panel();
		awayImg.setBounds(210, 90, 10, 10);
		add(awayImg);
		
		homeTeamLbl = new JLabel("");
		homeTeamLbl.setBounds(20, 50, 92, 26);
		add(homeTeamLbl);
		
		awayTeamLbl = new JLabel("");
		awayTeamLbl.setBounds(175, 50, 92, 26);
		add(awayTeamLbl);
		
		timeLbl = new JLabel("");
		timeLbl.setBounds(93, 110, 92, 26);
		add(timeLbl);
		
		locationLbl = new JLabel("");
		locationLbl.setBounds(93, 140, 92, 26);
		add(locationLbl);
		
		homeScoreLbl = new JLabel("0");
		homeScoreLbl.setBounds(42, 115, 30, 26);
		add(homeScoreLbl);
		
		awayScoreLbl = new JLabel("0");
		awayScoreLbl.setBounds(201, 115, 30, 26);
		add(awayScoreLbl);
	}
}
