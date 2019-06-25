package com.sportstracker.border;

import java.util.List;
import com.sportstracker.entities.*;

public interface ISportDatabase
{
	/**
	 * Gets a list of all matches in the database
	 * @return Returns a List<Match> containing all matches in the database
	 */
	public List<Match> getAllMatches();
	public List<Player> getAllPlayers();
	public List<Team> getAllTeams();
	/**
	 * Adds a single match to the database
	 * @param match The match to add
	 * @return Returns the id of the match that was added
	 */
	public int addMatch(Match match);
}
