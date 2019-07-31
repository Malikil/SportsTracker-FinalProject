package database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sportstracker.controller.AdminController;

class DatabaseTests
{
	@Test
	void addDatabaseItems()
	{
		boolean teams, matches, players;
		//teams = addTeams();
		//matches = addMatches();
		players = addPlayer();
		
		assertTrue(/*teams && matches &&*/ players);
	}
	
	private boolean addPlayer()
	{
		AdminController db = new AdminController();
		return (/*db.addNewPlayer("Robert", "Lewandoski", "FW", "Bayern Munich", 9, 29, 187)&&
			    db.addNewPlayer("Alphonso", "Davies", "MF", "Bayern Munich", 19, 18, 181)&&
			    db.addNewPlayer("Manuel", "Neuer", "GK", "Bayern Munich", 1, 33, 193)&&
			    db.addNewPlayer("J�r�me", "Boateng", "DF", "Bayern Munich", 1, 30, 192)&&
			    db.addNewPlayer("Thomas", "Muller", "FW", "Bayern Munich", 25, 29, 186)&&
			    db.addNewPlayer("Benjamin", "Pavard", "DF", "Bayern Munich", 5, 23, 186)&&
			    db.addNewPlayer("Thiago", "Nascimento", "MF","Bayern Munich", 6, 28, 174)&&
			    db.addNewPlayer("Javi", "Martinez", "DF", "Bayern Munich", 8, 30, 190)&&
			    db.addNewPlayer("Joshua", "Kimmich", "DF", "Bayern Munich", 32, 24, 176)&&
			    db.addNewPlayer("Jann-Fiet", "Arp", "FW", "Bayern Munich", 15,  19, 186)&&
			    db.addNewPlayer("Sven", "Ulreich", "GK", "Bayern Munich", 26, 30, 192)&&
			    db.addNewPlayer("David", "Alaba", "DF", "Bayern Munich", 27, 27, 180)&&
			    db.addNewPlayer("Christian", "Fruchtl", "GK", "Bayern Munich", 36, 19, 193)&&
			    db.addNewPlayer("David", "Silva", "MF", "Man. City" , 21, 33, 170)&&
			    db.addNewPlayer("Claudio", "Bravo", "GK", "Man. City", 1, 36, 184)&&
			    db.addNewPlayer("Kyle", "Walker", "DF", "Man. City" , 2, 29, 178)&&
			    db.addNewPlayer("Sergio","Aguero", "FW", "Man. City", 10, 31, 173)&&
			    db.addNewPlayer("George", "Woods", "GK", "Man. City", 37, 29, 175)&&
			    db.addNewPlayer("John", "Stone", "DF", "Man. City", 5, 25, 188)&&
			    db.addNewPlayer("Raheem", "Sterling", "FW", "Man. City", 7, 24, 170)&&
			    db.addNewPlayer("Gabriel", "Jesus", "FW", "Man. City", 9, 22, 175)&&
			    db.addNewPlayer("Kevin", "De Bruyne", "MF", "Man. City", 17, 28, 181)&&
			    db.addNewPlayer("Leroy", "Sane", "MF", "Man. City", 19, 23, 183)&&
			    db.addNewPlayer("Nicolas", "Otamendi", "DF", "Man. City", 30, 31, 183)&&
			    db.addNewPlayer("Daniel", "Grimshaw", "GK", "Man. City", 32, 20, 180)&&
			    db.addNewPlayer("Lionel","Messi", "FW", "Barcelona", 10, 32, 170)&&
			    db.addNewPlayer("Marc-Andre", "ter Stegen", "GF", "Barcelona", 1, 27, 187)&&
			    db.addNewPlayer("Nelson", "Semedo", "DF", "Barcelona", 2, 25, 177)&&
			    db.addNewPlayer("Gerard", "Pique", "DF", "Barcelona", 3, 32, 194)&&
			    db.addNewPlayer("Ivan", "Rakitic", "MF", "Barcelona", 4, 31, 184)&&
			    db.addNewPlayer("Sergio", "Busquets", "MF", "Barcelona", 5, 31, 189)&&
			    db.addNewPlayer("Jean-Clair", "Todibo", "DF", "Barcelona" , 6, 19, 190)&&
			    db.addNewPlayer("Philippe", "Coutinho", "MF", "Barcelona", 7, 27, 172)&&
			    db.addNewPlayer("Luis", "Suarez", "FW", "Barcelona", 9, 32, 182)&&
			    db.addNewPlayer("Antoine", "Griezmann", "FW", "Barcelona", 17, 28, 176)&&
			    db.addNewPlayer("Norberto","Neto","GK", "Barcelona", 13, 30, 190)&&
			    db.addNewPlayer("Malcom", "de Oliveira", "FW", "Barcelona", 14, 22, 172)&&
			    db.addNewPlayer("Cristiano", "Ronaldo", "FW", "Juventus", 7, 33, 187)&&
			    db.addNewPlayer("Wojciech", "Szczesny", "Gk", "Juventus", 1, 29, 195)&&
			    db.addNewPlayer("Giorgio", "Chiellini", "DF", "Juventus", 3, 34, 187)&&
			    db.addNewPlayer("Aaron", "Ramsey", "MF", "Juventus", 8, 28, 178)&&
			    db.addNewPlayer("Matthija", "de Ligt", "DF", "Juventus", 4, 19, 189)&&
			    db.addNewPlayer("Miralem", "Pjanic", "MF", "Juventus", 5, 29, 180)&&
			    db.addNewPlayer("Sami", "Khedira", "MF", "Juventus", 6, 32, 189)&&
			    db.addNewPlayer("Paulo", "Dybala", "FW", "Juventus", 10, 25, 177)&&
			    db.addNewPlayer("Douglas", "Costa", "FW", "Juventus", 11, 28, 172)&&
			    db.addNewPlayer("Alex", "Sandro", "DF", "Juventus", 12, 28, 177)&&
			    db.addNewPlayer("Blaise", "Matuidi", "MF", "Juventus", 14, 32, 180)&&
			    db.addNewPlayer("Juan", "Cuadrado", "MF", "Juventus", 16, 31, 176)&&
			    db.addNewPlayer("Mattia", "Perin", "GK", "Juventus", 22, 26, 188)&&
			    db.addNewPlayer("Carlo", "Pinsoglio", "GK", "Juventus", 31, 29, 194)&&
			    db.addNewPlayer("Alisson", "Becker", "GK", "Liverpool", 1, 26, 193)&&
			    db.addNewPlayer("Nathaniel", "Clyne", "DF", "Liverpool", 2, 28, 175)&&
			    db.addNewPlayer("Fabinho", "Tavares", "MF", "Liverpool", 3,25,188)&&
			    db.addNewPlayer("Roberto", "Firmino", "FW", "Liverpool", 9, 27,181)&&
			    db.addNewPlayer("Keyler", "Navas", "GK", "Real Madrid", 1, 32, 185)&&*/
			    db.addNewPlayer("Sergio", "Ramos", "DF", "Real Madrid", 4, 33, 184)&&
			    db.addNewPlayer("Luka", "Mordric", "MF", "Real Madrid", 10, 33, 172)&&
			    db.addNewPlayer("Gareth", "Bale", "FW", "Real Madrid", 11, 30, 185)&&
			    db.addNewPlayer("Kepa", "Arrizabalaga", "GK", "Chelsea", 1, 24, 186)&&
			    db.addNewPlayer("Marcos", "Alonso", "DF", "Chelsea", 3, 28, 188)&&
			    db.addNewPlayer("Danny", "Drinkwater", "MF", "Chelsea", 6, 29, 177)&&
			    db.addNewPlayer("Pedro", "Ledesma", "FW", "Chelsea", 11, 31, 169));
			    
	}
	
	private boolean addTeams()
	{
		AdminController db = new AdminController();
		return (db.addNewTeam("Liverpool") &&
				db.addNewTeam("Man. City") &&
				db.addNewTeam("Real Madrid") &&
				db.addNewTeam("Chelsea") &&
				db.addNewTeam("Barcelona") &&
				db.addNewTeam("Juventus") &&
				db.addNewTeam("Bayern Munich"));
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
				db.addNewMatch("Man. City", "Real Madrid", "", "", "1/08/2019") &&
				db.addNewMatch("Liverpool", "Chelsea", "", "", "1/08/2019") &&
				db.addNewMatch("Barcelona", "Juventus", "", "", "1/08/2019"));
		// Matches on the 2nd
		boolean ninteenth = (
				db.addNewMatch("Chelsea", "Man. City", "", "", "2/08/2019") &&
				db.addNewMatch("Juventus", "Real Madrid", "", "", "2/08/2019") &&
				db.addNewMatch("Barcelona", "Liverpool", "", "", "2/08/2019"));
		return (fifteenth && sixteenth
				&& seventeenth && eighteenth
				&& ninteenth);
	}
}
