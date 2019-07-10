package com.sportstracker.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;

import com.sportstracker.border.ISportDatabase;
import com.sportstracker.border.SportsDAO;
import com.sportstracker.entities.Match;

public class MatchManager 
{
	public ArrayList<Match> getPastMatches()
	{
		ArrayList<Match> matches = null;
		try
		{
			// gets list of matches (up coming, past, followed)
			ISportDatabase db = new SportsDAO();
			matches = (ArrayList<Match>) db.getMatchesBeforeDate(new Date());
			// Get the user from the database, will be null if user/pass
			// didn't match any account
			
		}
		catch (HibernateException hx)
		{
			JOptionPane.showMessageDialog(null,
					"There was a problem connecting to the server", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return matches;
	}
	
	public ArrayList<Match> getUpcomingMatches()
	{
		ArrayList<Match> matches = null;
		
		try
		{
			// gets list of matches (up coming, past, followed)
			ISportDatabase db = new SportsDAO();
			matches = (ArrayList<Match>) db.getMatchesAfterDate(new Date());
			
			// Get the user from the database, will be null if user/pass
			// didn't match any account
			
		}
		catch (HibernateException hx)
		{
			JOptionPane.showMessageDialog(null,
					"There was a problem connecting to the server", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return matches;
	}
	
	
}