package com.sportstracker.border;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sportstracker.entities.*;

public interface ISportDatabase
{
	/**
	 * Gets a list of all matches in the database
	 * @return Returns an ArrayList&lt;Match&gt; containing all matches in the database
	 */
	public ArrayList<Match> getAllMatches();
	public ArrayList<Player> getAllPlayers();
	public ArrayList<Team> getAllTeams();
	/**
	 * Adds a single match to the database
	 * @param match The match to add
	 * @return Returns the id of the match that was added
	 */
	public Integer createMatch(Match match);
	
	public List<Match> getMatchesBeforeDate(Date date);
	public List<Match> getMatchesAfterDate(Date date);
}
