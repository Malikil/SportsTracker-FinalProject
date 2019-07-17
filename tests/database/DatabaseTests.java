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
		// Matches on the 15th
		boolean fifteenth = (
				db.addNewMatch("Juventus", "Liverpool", "2", "5", "15/07/2019") &&
				db.addNewMatch("Man. City", "Barcelona", "8", "3", "15/07/2019") &&
				db.addNewMatch("Real Madrid", "Chelsea", "0", "3", "15/07/2019"));
		// Matches on the 16th
		boolean sixteenth = (
				db.addNewMatch("Barcelona", "Real Madrid", "2", "2", "16/07/2019") &&
				db.addNewMatch("Chelsea", "Juventus", "3", "1", "16/07/2019") &&
				db.addNewMatch("Liverpool", "Man. City", "0", "0", "16/07/2019"));
		// Matches on the 17th
		boolean seventeenth = (
				db.addNewMatch("Chelsea", "Barcelona", "", "", "17/07/2019") &&
				db.addNewMatch("Real Madrid", "Liverpool", "", "", "17/07/2019") &&
				db.addNewMatch("Juventus", "Man. City", "", "", "17/07/2019"));
		// Matches on the 18th
		boolean eighteenth = (
				db.addNewMatch("Man. City", "Real Madrid", "", "", "18/07/2019") &&
				db.addNewMatch("Liverpool", "Chelsea", "", "", "18/07/2019") &&
				db.addNewMatch("Barcelona", "Juventus", "", "", "18/07/2019"));
		// Matches on the 19th
		boolean ninteenth = (
				db.addNewMatch("Chelsea", "Man. City", "", "", "19/07/2019") &&
				db.addNewMatch("Juventus", "Real Madrid", "", "", "19/07/2019") &&
				db.addNewMatch("Barcelona", "Liverpool", "", "", "19/07/2019"));
		return (fifteenth && sixteenth
				&& seventeenth && eighteenth
				&& ninteenth);
	}
}
