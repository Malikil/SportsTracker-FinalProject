package com.sportstracker.border;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChangePasswordPanel
{
	private JPanel frame;
	private JTextField oldPass;
	private JTextField newPass;
	private JTextField confirm;
	
	public JPanel getFrame()
	{ return frame; }
	public String getNewPassword()
	{ return newPass.getText(); }
	public String getOldPassword()
	{ return oldPass.getText(); }
	public boolean passwordsMatch()
	{
		return newPass.getText().equals(confirm.getText());
	}
	
	public ChangePasswordPanel()
	{
		initialize();
	}
	
	private void initialize()
	{
		frame = new JPanel();
		frame.setLayout(new GridLayout(3, 2));
		
		frame.add(new JLabel("Old Password:"));
	}
}
