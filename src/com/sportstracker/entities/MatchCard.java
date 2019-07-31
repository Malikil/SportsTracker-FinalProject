package com.sportstracker.entities;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MatchCard extends JPanel
{
	//private Panel homeImg;
	//private Panel awayImg;
	private JLabel homeTeamLbl;
	private JLabel awayTeamLbl;
	private JLabel timeLbl;
	//private JLabel locationLbl;
	private JLabel homeScoreLbl;
	private JLabel awayScoreLbl;
	
	/**
	 * Create the card with the given match information.
	 */
	public MatchCard(Match nMatch) {
		
		initialize();
		
		homeTeamLbl.setText(nMatch.getHomeTeam().getTeamName());
		awayTeamLbl.setText(nMatch.getAwayTeam().getTeamName());
		if (new Date().after(nMatch.getTime())
				&& nMatch.getHomeScore() != null // Check for null because the database
				&& nMatch.getAwayScore() != null)// isn't always getting updated
		{
			// If the match hasn't happened yet, don't show scores
			homeScoreLbl.setText(Integer.toString(nMatch.getHomeScore()));
			awayScoreLbl.setText(Integer.toString(nMatch.getAwayScore()));
		}
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyy");
		timeLbl.setText(f.format(nMatch.getTime()));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLayout(new GridBagLayout());
		
		JLabel lblHomeTeam = new JLabel("Home Team");
		lblHomeTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 10, 0, 0);
		add(lblHomeTeam, c);
		
		JLabel lblAwayTeam = new JLabel("Away Team");
		lblAwayTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		//lblAwayTeam.setBounds(176, 15, 92, 26);
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.insets = new Insets(10, 0, 0, 10);
		add(lblAwayTeam, c);
		
		homeTeamLbl = new JLabel();
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(0, 10, 0, 0);
		add(homeTeamLbl, c);
		
		JLabel lblVs = new JLabel("-VS-");
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(0, 5, 0, 5);
		add(lblVs, c);
		
		awayTeamLbl = new JLabel();
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(0, 0, 0, 10);
		add(awayTeamLbl, c);
		
		homeScoreLbl = new JLabel();
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_END;
		add(homeScoreLbl, c);
		
		awayScoreLbl = new JLabel();
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		add(awayScoreLbl, c);
		
		timeLbl = new JLabel();
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(0, 0, 10, 0);
		add(timeLbl, c);
		
		//setPreferredSize(new Dimension(289, 170));
		//setLayout(null);
		
		/*homeImg = new Panel();
		homeImg.setBounds(50, 90, 10, 10);
		add(homeImg);
		
		awayImg = new Panel();
		awayImg.setBounds(210, 90, 10, 10);
		add(awayImg);*/
		
		/*locationLbl = new JLabel("");
		locationLbl.setBounds(93, 140, 92, 26);
		add(locationLbl);*/
	}
}
