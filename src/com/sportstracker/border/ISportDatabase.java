package com.sportstracker.border;

import java.util.List;
import com.sportstracker.entities.*;

public interface ISportDatabase
{
	public List<Match> getAllMatches();
	public List<Player> getAllPlayers();
	public List<Team> getAllTeams();
}
