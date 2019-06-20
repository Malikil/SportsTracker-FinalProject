package com.sportstracker.controller;

import javax.swing.JOptionPane;
import com.sportstracker.border.Login;

public class LoginManager
{
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
	
	public static int createAccount(String uname, String pword)
	{
		return -2;
	}
}
