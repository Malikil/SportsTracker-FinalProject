package com.sportstracker.border;

import java.util.ArrayList;
import com.sportstracker.entities.*;

public interface ISportDatabase
{
	/**
	 * Gets a list of all matches in the database
	 * @return Returns an ArrayList<Match> containing all matches in the database
	 */
	public ArrayList<Match> getAllMatches();
	public ArrayList<Player> getAllPlayers();
	public ArrayList<Team> getAllTeams();
	/**
	 * Adds a single match to the database
	 * @param match The match to add
	 * @return Returns the id of the match that was added
	 */
	public int addMatch(Match match);
}
