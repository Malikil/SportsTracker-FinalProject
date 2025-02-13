package com.sportstracker.entities;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.Font;

/**
 * Shows all the information for a given team. The tab can be closed later.
 */
public abstract class CloseableTab extends JPanel
{
	private JButton closeButton;
	private JLabel titleLabel;
	
	/**
	 * Create an instance of a CloseableTab with the given title
	 * @param title What should appear in the upper corner of the frame
	 */
	public CloseableTab(String title)
	{
		setLayout(new GridBagLayout());
		// Team name in the top left
		titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		//c.weightx = 0.5;
		//c.weighty = 0.5;
		add(titleLabel, c);
		// Close button in the top right
		closeButton = new JButton("Close tab");
		c = new GridBagConstraints();
		c.gridx = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		//c.weightx = 0.5;
		//c.weighty = 0.5;
		add(closeButton, c);
	}
	
	public String getTabTitle()
	{
		return titleLabel.getText();
	}
	
	public void addCloseTabListener(ActionListener l)
	{
		closeButton.addActionListener(l);
	}
	
	protected void addContent(JPanel panel)
	{
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 1; c.gridwidth = 2;
		c.weightx = 1.0; c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		add(panel, c);
	}
}
