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
		{
			return null;
		}
	}
}
