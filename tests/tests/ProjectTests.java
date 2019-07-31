package tests;

import com.sportstracker.border.SportsDAO;
import com.sportstracker.controller.AdminController;
import com.sportstracker.controller.DatabaseController;
import com.sportstracker.controller.UserManager;
import com.sportstracker.entities.User;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProjectTests
{
	
	@Test
	void createUser()
	{
		UserManager lm = new UserManager();
		
		String username = "GabeNewell";
		String password = "Steam";
		lm.createAccount(username, password);
		
		assertTrue(lm.tryLogin(username, password) != null);
		
	}
	
	@Test
	void passwordChange()
	{
		SportsDAO sd = new SportsDAO();
		UserManager lm = new UserManager();
		String username = "GabeNewell";
		String oldPassword = "Steam";
		String newPassword = "Sales";
		
		lm.changePassword(username, oldPassword, newPassword);
		
		assertTrue(lm.tryLogin(username, newPassword) != null);
		
		lm.changePassword(username, newPassword, oldPassword);
		
		assertTrue(lm.tryLogin(username, oldPassword) != null);
	}
	
	@Test
	void loginAdmin()
	{
		UserManager lm = new UserManager();
		User user = lm.tryLogin("User", "Pass");
		assertTrue(user != null && user.isAdmin());
	}
	
	@Test
	void loginBasic()
	{
		UserManager lm = new UserManager();
		User user = lm.tryLogin("Basic", "Pass");
		assertTrue(user != null && !user.isAdmin());
	}

	@Test
	void loginNoUser()
	{
		UserManager lm = new UserManager();
		User user = lm.tryLogin("NoUserExists", "RandomPass");
		assertNull(user);
	}
	
	@Test
	void loginWrongPass()
	{
		UserManager lm = new UserManager();
		User user = lm.tryLogin("User", "RandomPass");
		assertNull(user);
	}
	
	@Test
	void createDuplicateAccount()
	{
		UserManager lm = new UserManager();
		
		String username = "User";
		String password = "Pass";
		assertFalse(lm.createAccount(username, password));
		
	}
	
	@Test
	void getPlayerByName()
	{
		SportsDAO db = new SportsDAO();
		assertNotNull(db.getPlayerByName("Boris Johnston"));
	}
	
	@Test
	void addNewTeam()
	{
		AdminController ac = new AdminController();
		assertTrue(ac.addNewTeam("WhiteCaps"));
	}
	
	@Test
	void getTeamNameList()
	{
		DatabaseController dc = new DatabaseController();
		assertNotNull(dc.getTeamnameList());
	}
	
	@Test
	void getPastTeamMatches()
	{
		DatabaseController dc = new DatabaseController();
		assertNotNull(dc.getPastTeamMatches("Chelsea"));
	}
	
	@Test
	void getUpcomingTeamMatches()
	{
		DatabaseController dc = new DatabaseController();
		assertNotNull(dc.getUpcomingTeamMatches("Chelsea"));
	}
	
	@Test
	void getTeamPlayers()
	{
		DatabaseController dc = new DatabaseController();
		assertNotNull(dc.getTeamPlayers("Chelsea"));
	}
	
	@Test
	void getAllPlayers()
	{
		DatabaseController dc = new DatabaseController();
		assertNotNull(dc.getAllPlayers());
	}
}
