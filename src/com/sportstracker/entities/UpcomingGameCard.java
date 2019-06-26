package com.sportstracker.entities;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UpcomingGameCard extends JPanel {
	private JTextField textField;
	/**
	 * Create the application.
	 */
	public UpcomingGameCard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setPreferredSize(new Dimension(289, 134));
		setLayout(null);
		
		JLabel lblHomeTeam = new JLabel("Home Team:");
		lblHomeTeam.setBounds(31, 40, 87, 16);
		add(lblHomeTeam);
		
		JLabel lblAwayTeam = new JLabel("Away Team:");
		lblAwayTeam.setBounds(31, 88, 87, 16);
		add(lblAwayTeam);
		
		textField = new JTextField();
		textField.setBounds(137, 37, 116, 22);
		add(textField);
		textField.setColumns(10);
	}
}
