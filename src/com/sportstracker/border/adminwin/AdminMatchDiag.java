package com.sportstracker.border.adminwin;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;

import com.sportstracker.entities.Match;
import com.sportstracker.entities.Player;

public class AdminMatchDiag
{
	private JPanel frame;
	private JComboBox<String> homeTeamList;
	private JComboBox<String> awayTeamList;
	private JTextField homeScoreEntry;
	private JTextField awayScoreEntry;
	private JTextField timeEntry;
	
	public AdminMatchDiag(List<String> teamList, Match m)
	{
		this(teamList);
		homeTeamList.setSelectedItem(m.getHomeTeam().getTeamName());
		awayTeamList.setSelectedItem(m.getAwayTeam().getTeamName());
		if (m.getHomeScore() != null)
			homeScoreEntry.setText(m.getHomeScore().toString());
		if (m.getAwayScore() != null)
			awayScoreEntry.setText(m.getAwayScore().toString());
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyy");
		timeEntry.setText(f.format(m.getTime()));
	}
	/**
	 * @wbp.parser.constructor
	 * @param teamList The list of available teams for the dropdown list 
	 */
	public AdminMatchDiag(List<String> teamList)
	{
		initialize();
		for (String s : teamList)
		{
			homeTeamList.addItem(s);
			awayTeamList.addItem(s);
		}
	}
	
	public String getHomeTeam()
	{ return (String)homeTeamList.getSelectedItem(); }
	public String getAwayTeam()
	{ return (String)awayTeamList.getSelectedItem(); }
	public Integer getHomeScore() {
		if (!homeScoreEntry.getText().isEmpty())
			return Integer.parseInt(homeScoreEntry.getText());
		return null;
	}
	public Integer getAwayScore() {
		if (!awayScoreEntry.getText().isEmpty())
			return Integer.parseInt(awayScoreEntry.getText());
		return null;
	}
	public Date getTime() throws ParseException
	{
		Date matchTime = null;
		matchTime = new SimpleDateFormat("dd/MM/yyyy").parse(timeEntry.getText());
		return matchTime;
	}
	
	public boolean showDialog()
	{
		String[] buttons = { "Ok", "Cancel" };
		return JOptionPane.showOptionDialog(null,
				frame, "Update match",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null,
				buttons, buttons[0]) == 0;
	}
	
	private void initialize()
	{
		frame = new JPanel(new GridBagLayout());
		GridBagConstraints c;
		
		JLabel lblHomeTeam = new JLabel("Home Team");
		lblHomeTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 0; c.weightx = 0.5;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 10, 0, 0);
		frame.add(lblHomeTeam, c);
		
		JLabel lblAwayTeam = new JLabel("Away Team");
		lblAwayTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		c = new GridBagConstraints();
		c.gridx = 4; c.gridy = 0; c.weightx = 0.5;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.insets = new Insets(10, 0, 0, 10);
		frame.add(lblAwayTeam, c);
		
		homeTeamList = new JComboBox<>();
		c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 1;
		c.weightx = 0.5; c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 10, 0, 0);
		frame.add(homeTeamList, c);
		
		JLabel lblVs = new JLabel("-VS-");
		c = new GridBagConstraints();
		c.gridx = 2; c.gridy = 1;
		c.insets = new Insets(0, 5, 0, 5);
		frame.add(lblVs, c);
		
		awayTeamList = new JComboBox<>();
		c = new GridBagConstraints();
		c.gridx = 3; c.gridy = 1;
		c.weightx = 0.5; c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 10);
		frame.add(awayTeamList, c);
		
		homeScoreEntry = new JTextField();
		c = new GridBagConstraints();
		c.gridx = 1; c.gridy = 2; c.weightx = 0.75;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5, 0, 0, 0);
		frame.add(homeScoreEntry, c);
		
		awayScoreEntry = new JTextField();
		awayScoreEntry.setMinimumSize(new Dimension(25, 20));
		c = new GridBagConstraints();
		c.gridx = 3; c.gridy = 2; c.weightx = 0.75;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(5, 0, 0, 0);
		frame.add(awayScoreEntry, c);
		
		timeEntry = new JTextField();
		c = new GridBagConstraints();
		c.gridx = 1; c.gridy = 3;
		c.gridwidth = 3; //c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(5, 0, 10, 0);
		frame.add(timeEntry, c);
	}
}
