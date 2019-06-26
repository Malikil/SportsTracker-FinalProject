package com.sportstracker.controller;

import java.awt.EventQueue;

import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

import com.sportstracker.border.*;
import com.sportstracker.entities.User;

public class LoginManager
{
	/**
	 * Used to show state of the current login attempt
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
		String[] buttons = { "Login", "Cancel" };
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
				IUserDatabase db = new SportsDAO();
				// Get the user from the database, will be null if user/pass
				// didn't match any account
				User user = db.getLogin(uname, pword);
				if (user != null)
					if (user.isAdmin())
						loginStatus = LoginStatus.ADMIN_USER;
					else
						loginStatus = LoginStatus.BASIC_USER;
			}
			// None here means they tried to log in but failed
			if (loginStatus == LoginStatus.NONE)
				JOptionPane.showMessageDialog(null,
						"Username or password is incorrect", "Login",
						JOptionPane.PLAIN_MESSAGE);
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
			String inserted = db.createAccount(username, password);
			if (inserted == null)
				return false;
			else
				return true;
		}
		catch (HibernateException hx)
		{ return null; }
		catch (PersistenceException px)
		{
			if (px.getCause() instanceof ConstraintViolationException)
				return false;
			else
				return null;
		}
	}
}
