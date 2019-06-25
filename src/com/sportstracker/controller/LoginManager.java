package com.sportstracker.controller;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;

import com.sportstracker.border.*;

public class LoginManager
{
	/**
	 * This is the main entry point for the application
	 * @param args Not used
	 */
	public static void main(String[] args)
	{
		Login login = new Login();
		String[] buttons = { "Login", "Cancel" };
		int loginStatus = -1;
		while (loginStatus == -1)
		{
			loginStatus = JOptionPane.showOptionDialog(null,
									login.getLoginPanel(), "Login",
									JOptionPane.OK_CANCEL_OPTION,
									JOptionPane.PLAIN_MESSAGE, null,
									buttons, buttons[0]);
			if (loginStatus == 0)
			{
				// Login here
				// if !logged in => loginStatus = -1
				String uname = login.getUsername();
				String pword = login.getPassword();
				System.out.println("User entered " + uname + ":" + pword);
			}
		}
	}
	
	/**
	 * Attempt to create an account in the database. A code will be returned
	 * relating to whether the account was created or not.
	 * @param username The user's desired username
	 * @param password The user's desired password
	 * @return A status code from the following:
	 * <ul>
	 * <li>The row number of a successfully inserted entry</li>
	 * <li>-1 for item already exists</li>
	 * <li>-2 if there was some other form of database error</li>
	 * </ul>
	 */
	public static int createAccount(String username, String password)
	{
		try
		{
			IUserDatabase db = new SportsDAO();
			Integer inserted = db.createAccount(username, password);
			if (inserted == null)
				return -1;
			else
				return inserted;
		}
		catch (HibernateException hx)
		{
			return -2;
		}
	}
}
