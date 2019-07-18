package com.sportstracker.controller;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import com.sportstracker.border.*;
import com.sportstracker.entities.User;

/**
 * This class will handle user accounts and logging in.
 * This is also the main entry point for the application.
 */
public class LoginManager
{
	/**
	 * Used to indicate state of the current login attempt
	 */
	private enum LoginStatus
	{
		NONE, ADMIN_USER, BASIC_USER, CANCELLED
	}
	
	/**
	 * This is the main entry point for the application
	 * @param args Not used
	 */
	public static void main(String[] args)
	{
		// Create an instance of the panel to be used for login
		Login login = new Login();
		String[] buttons = {"Login", "Cancel" };
		LoginStatus loginStatus = LoginStatus.NONE;
		while (loginStatus == LoginStatus.NONE)
		{
			// If the user clicks the ok button, try to log in. If they click
			// cancel just quit out.
			// Display the login panel as the content of the dialog box
			if (JOptionPane.showOptionDialog(null,
									login.getLoginPanel(), "Login",
									JOptionPane.OK_CANCEL_OPTION,
									JOptionPane.PLAIN_MESSAGE, null,
									buttons, buttons[0]) != 0)
				loginStatus = LoginStatus.CANCELLED;
			else
			{
				// Login here
				String uname = login.getUsername();
				String pword = login.getPassword();
				User user = null;
				try
				{
					user = tryLogin(uname, pword);
					if (user != null)
						if (user.isAdmin())
							loginStatus = LoginStatus.ADMIN_USER;
						else
							loginStatus = LoginStatus.BASIC_USER;
					else // User is null or password is wrong
						JOptionPane.showMessageDialog(null,
								"Username or password is incorrect", "Login",
								JOptionPane.WARNING_MESSAGE);
				}
				catch (HibernateException hx)
				{
					JOptionPane.showMessageDialog(null,
							"There was a problem connecting to the server", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		// If they didn't cancel logging in by this point, they must *be*
		// logged in. Show the main window, including admin tab if appropriate
		if (loginStatus != LoginStatus.CANCELLED)
		{
			final boolean isAdmin = loginStatus == LoginStatus.ADMIN_USER;
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						SportTrackerMain window = new SportTrackerMain(isAdmin);
						window.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	public static User tryLogin(String username, String password)
	{
		IUserDatabase userdb = new SportsDAO();
		// Get the user from the database, will be null if user/pass
		// didn't match any account
		User user = userdb.getUser(username);
		if (user != null && user.getPassword().equals(password))
			return user;
		else
			return null;
	}
	
	/**
	 * Attempt to create an account in the database. A bool will be returned
	 * relating to whether the account was created or not.
	 * @param username The user's desired username
	 * @param password The user's desired password
	 * @return True or false depending on if insertion was completed<br>
	 * Returns null if a hibernate exception was encountered
	 */
	public static Boolean createAccount(String username, String password)
	{
		try
		{
			IUserDatabase db = new SportsDAO();
			// First check if the user exists already
			if (db.getUser(username) != null)
				return false;
			String inserted = db.createAccount(username, password);
			if (inserted == null)
				return false;
			else
				return true;
		}
		catch (HibernateException hx)
		{ return null; }
	}
}
