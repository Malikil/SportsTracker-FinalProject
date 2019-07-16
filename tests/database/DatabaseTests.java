package database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sportstracker.controller.AdminController;

class DatabaseTests
{
	@Test
	void addTeams()
	{
		AdminController db = new AdminController();
		assertTrue(db.addNewTeam("Liverpool") &&
				db.addNewTeam("Man. City") &&
				db.addNewTeam("Real Madrid") &&
				db.addNewTeam("Chelsea"));
	}
	
	@Test
	void addMatches()
	{
		AdminController db = new AdminController();
		// Matches on the 15th
		boolean fifteenth = (
				db.addNewMatch("Liverpool", "Man. City", "3", "0", "")
				);
		// Matches on the 16th
		// Matches on the 17th
		// Matches on the 18th
		// Matches on the 19th
		assertTrue(fifteenth);
	}
}
