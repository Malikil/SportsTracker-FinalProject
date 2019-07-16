package com.sportstracker.border;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;

import com.sportstracker.controller.LoginManager;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A container for a simple login screen, which can be used to display on
 * something like a JOptionPane. It will store the entered username and
 * password so they can be used later. (ie to log in with)
 */
public class Login
{
	private String passConfirm = null;
	
	private JPanel frame;
	private JTextField txtUsername;
	private JPasswordField pwdField;

	/**
	 * Gets a panel with username/password text boxes that can be shown
	 * in places like other forms or dialog boxes
	 * @return A panel that can be displayed with username and password input boxes
	 */
	public JPanel getLoginPanel()
	{ return frame;	}
	public String getUsername()
	{ return txtUsername.getText(); }
	public String getPassword()
	{ return String.valueOf(pwdField.getPassword()); }

	/**
	 * calls initialize to begin login process
	 */
	public Login()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JPanel();
		frame.setPreferredSize(new Dimension(350, 150));
		frame.setLayout(null);
		
		JLabel lblPleaseLoginOr = new JLabel("Enter Login Information");
		lblPleaseLoginOr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPleaseLoginOr.setBounds(12, 37, 155, 23);
		frame.add(lblPleaseLoginOr);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsername.setBounds(32, 82, 70, 23);
		frame.add(lblUsername);
		
		JLabel lblPwd = new JLabel("Password:");
		lblPwd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPwd.setBounds(32, 116, 70, 23);
		frame.add(lblPwd);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(114, 83, 106, 20);
		frame.add(txtUsername);
		txtUsername.setColumns(10);
		
		pwdField = new JPasswordField();
		pwdField.setBounds(114, 116, 106, 21);
		frame.add(pwdField);
		
		/**
		 * Creates button used to create new accounts
		 */
		JButton btnSignUp = new JButton("Create Account");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passConfirm = JOptionPane.showInputDialog(null,
						"Please re-enter password",
						"Confirm",
						JOptionPane.OK_CANCEL_OPTION);
				/**
				 * confirms user has entered a password matching the previous entry
				 */
				if (passConfirm != null)
				{
					if (!passConfirm.equals(String.valueOf(pwdField.getPassword())))
						JOptionPane.showMessageDialog(null,
								"Passwords do not match", "Info",
								JOptionPane.WARNING_MESSAGE);
					else
					{
						// Try to create an account using LoginManager
						Boolean result = LoginManager.createAccount(getUsername(), getPassword()); 
						if (result == null)
							JOptionPane.showMessageDialog(null,
									"An unknown error occured.", "Register",
									JOptionPane.ERROR_MESSAGE);
						else if (result)
							JOptionPane.showMessageDialog(null,
									"Account was created.", "Register",
									JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null,
									"That username is already in use.", "Register",
									JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnSignUp.setBounds(205, 13, 128, 23);
		frame.add(btnSignUp);
	}
}
