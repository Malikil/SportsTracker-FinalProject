package com.sportstracker.entities;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

/**
 * Shows all the information for a given team. The tab can be closed later.
 */
public abstract class CloseableTab extends JPanel
{
	private JButton closeButton;
	
	/**
	 * Create an instance of a CloseableTab with the given title
	 * @param title What should appear in the upper corner of the frame
	 */
	public CloseableTab(String title)
	{
		setLayout(new GridBagLayout());
		// Team name in the top left
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		//c.weightx = 0.5;
		//c.weighty = 0.5;
		add(new JLabel(title));
		// Close button in the top right
		closeButton = new JButton("Close tab");
		c = new GridBagConstraints();
		c.gridx = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		//c.weightx = 0.5;
		//c.weighty = 0.5;
		add(closeButton, c);
	}
	
	public void addCloseTabListener(ActionListener l)
	{
		closeButton.addActionListener(l);
	}
}
