package database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sportstracker.controller.AdminController;

class DatabaseTests
{
	@Test
	void addDatabaseItems()
	{
		assertTrue(addTeams() &&
				addMatches());
	}
	
	private boolean addTeams()
	{
		AdminController db = new AdminController();
		return (db.addNewTeam("Liverpool") &&
				db.addNewTeam("Man. City") &&
				db.addNewTeam("Real Madrid") &&
				db.addNewTeam("Chelsea") &&
				db.addNewTeam("Barcelona") &&
				db.addNewTeam("Juventus"));
	}
	
	private boolean addMatches()
	{
		AdminController db = new AdminController();
		// Matches on the 29th
		boolean fifteenth = (
				db.addNewMatch("Juventus", "Liverpool", "2", "5", "29/07/2019") &&
				db.addNewMatch("Man. City", "Barcelona", "8", "3", "29/07/2019") &&
				db.addNewMatch("Real Madrid", "Chelsea", "0", "3", "29/07/2019"));
		// Matches on the 30th
		boolean sixteenth = (
				db.addNewMatch("Barcelona", "Real Madrid", "2", "2", "30/07/2019") &&
				db.addNewMatch("Chelsea", "Juventus", "3", "1", "30/07/2019") &&
				db.addNewMatch("Liverpool", "Man. City", "0", "0", "30/07/2019"));
		// Matches on the 31st
		boolean seventeenth = (
				db.addNewMatch("Chelsea", "Barcelona", "2", "5", "31/07/2019") &&
				db.addNewMatch("Real Madrid", "Liverpool", "7", "3", "31/07/2019") &&
				db.addNewMatch("Juventus", "Man. City", "0", "2", "31/07/2019"));
		// Matches on the 1st
		boolean eighteenth = (
				db.addNewMatch("Man. City", "Real Madrid", "5", "6", "1/08/2019") &&
				db.addNewMatch("Liverpool", "Chelsea", "1", "1", "1/08/2019") &&
				db.addNewMatch("Barcelona", "Juventus", "2", "3", "1/08/2019"));
		// Matches on the 2nd
		boolean ninteenth = (
				db.addNewMatch("Chelsea", "Man. City", "1", "5", "2/08/2019") &&
				db.addNewMatch("Juventus", "Real Madrid", "0", "0", "2/08/2019") &&
				db.addNewMatch("Barcelona", "Liverpool", "2", "4", "2/08/2019"));
		return (fifteenth && sixteenth
				&& seventeenth && eighteenth
				&& ninteenth);
	}
}
