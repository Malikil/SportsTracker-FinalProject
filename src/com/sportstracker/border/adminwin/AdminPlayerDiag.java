package com.sportstracker.border.adminwin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sportstracker.entities.Player;

public class AdminPlayerDiag
{
	private JPanel frame;
	private DefaultComboBoxModel<String> teams;
	private JTextField firstNameBox;
	private JTextField lastNameBox;
	private JTextField jerseyBox;
	private JTextField positionBox;
	private JTextField ageBox;
	private JTextField heightBox;
	
	public AdminPlayerDiag(List<String> teamList, Player p)
	{
		this(teamList);
		teams.setSelectedItem(p.getTeam().getTeamName());
		firstNameBox.setText(p.getFirstName());
		lastNameBox.setText(p.getLastName());
		jerseyBox.setText(Integer.toString(p.getJerseyNumber()));
		positionBox.setText(p.getPosition());
		ageBox.setText(Integer.toString(p.getAge()));
		heightBox.setText(Integer.toString(p.getHeight()));
	}
	/**
	 * @wbp.parser.constructor
	 * @param teamList The list of available teams for the dropdown list 
	 */
	public AdminPlayerDiag(List<String> teamList)
	{
		initialize();
		for (String s : teamList)
			teams.addElement(s);
	}
	
	public boolean showDialog()
	{
		String[] buttons = { "Ok", "Cancel" };
		return JOptionPane.showOptionDialog(null,
				frame, "Update players",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null,
				buttons, buttons[0]) == 0;
	}
	
	public String getTeam() {
		return (String)teams.getSelectedItem();
	}
	public String getFirstName() {
		return firstNameBox.getText();
	}
	public String getLastName() {
		return lastNameBox.getText();
	}
	public int getJersey() {
		return Integer.parseInt(jerseyBox.getText());
	}
	public String getPosition() {
		return positionBox.getText();
	}
	public int getAge() {
		return Integer.parseInt(ageBox.getText());
	}
	public int getHeight() {
		return Integer.parseInt(heightBox.getText());
	}
	
	private void initialize()
	{
		frame = new JPanel(new GridBagLayout());
		GridBagConstraints c;
		
		// ========== Team ==========
		c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.fill = GridBagConstraints.NONE;
		frame.add(new JLabel("Team"), c);
		
		teams = new DefaultComboBoxModel<>();
		c = new GridBagConstraints();
		c.gridx = 1; c.gridy = 0; c.weightx = 0.4;
		c.gridwidth = 2; c.insets = new Insets(0, 3, 0, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		frame.add(new JComboBox<String>(teams), c);
		
		// ========== Player name ==========
		c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0, 10, 0, 0);
		frame.add(new JLabel("Player Name"), c);
		
		firstNameBox = new JTextField();
		c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 2; c.weightx = 0.4;
		c.gridwidth = 2; c.insets = new Insets(0, 3, 0, 3);
		c.fill = GridBagConstraints.HORIZONTAL;
		frame.add(firstNameBox, c);
		
		lastNameBox = new JTextField();
		c = new GridBagConstraints();
		c.gridx = 2; c.gridy = 2; c.weightx = 0.4;
		c.gridwidth = 2; c.insets = new Insets(0, 0, 0, 3);
		c.fill = GridBagConstraints.HORIZONTAL;
		frame.add(lastNameBox, c);
		
		// ========== Jersey and position ==========
		c = new GridBagConstraints();
		c.gridx = 2; c.gridy = 3;
		c.anchor = GridBagConstraints.LINE_END;
		c.fill = GridBagConstraints.NONE;
		frame.add(new JLabel("Jersey"), c);
		
		jerseyBox = new JTextField();
		c = new GridBagConstraints();
		c.gridx = 3; c.gridy = 3; c.weightx = 0.2;
		c.insets = new Insets(3, 3, 3, 3);
		c.fill = GridBagConstraints.HORIZONTAL;
		frame.add(jerseyBox, c);
		
		c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 3;
		c.anchor = GridBagConstraints.LINE_END;
		c.fill = GridBagConstraints.NONE;
		frame.add(new JLabel("Position"), c);
		
		positionBox = new JTextField();
		c = new GridBagConstraints();
		c.gridx = 1; c.gridy = 3; c.weightx = 0.2;
		c.insets = new Insets(3, 3, 3, 3);
		c.fill = GridBagConstraints.HORIZONTAL;
		frame.add(positionBox, c);
		
		// ========== Age and height ==========
		c = new GridBagConstraints();
		c.gridx = 2; c.gridy = 4;
		c.anchor = GridBagConstraints.LINE_END;
		frame.add(new JLabel("Age"), c);
		
		ageBox = new JTextField();
		c = new GridBagConstraints();
		c.gridx = 3; c.gridy = 4; c.weightx = 0.2;
		c.insets = new Insets(0, 3, 0, 3);
		c.fill = GridBagConstraints.HORIZONTAL;
		frame.add(ageBox, c);
		
		c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 4;
		c.anchor = GridBagConstraints.LINE_END;
		frame.add(new JLabel("Height"), c);
		
		heightBox = new JTextField();
		c = new GridBagConstraints();
		c.gridx = 1; c.gridy = 4; c.weightx = 0.2;
		c.insets = new Insets(0, 3, 0, 3);
		c.fill = GridBagConstraints.HORIZONTAL;
		frame.add(heightBox, c);
	}
}
