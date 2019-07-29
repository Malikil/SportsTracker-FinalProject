package tests;

import com.sportstracker.border.SportsDAO;
import com.sportstracker.controller.LoginManager;
import com.sportstracker.entities.User;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProjectTests
{
	
	@Test
	void createUser()
	{
		LoginManager lm = new LoginManager();
		
		String username = "GabeNewell";
		String password = "Steam";
		lm.createAccount(username, password);
		
		assertTrue(lm.tryLogin(username, password) != null);
		
	}
	
	@Test
	void passwordChange()
	{
		SportsDAO sd = new SportsDAO();
		LoginManager lm = new LoginManager();
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
		LoginManager lm = new LoginManager();
		User user = lm.tryLogin("User", "Pass");
		assertTrue(user != null && user.isAdmin());
	}
	
	@Test
	void loginBasic()
	{
		LoginManager lm = new LoginManager();
		User user = lm.tryLogin("Basic", "Pass");
	}

	@Test
	void loginNoUser()
	{
		fail("Not implemented");
	}
	
	@Test
	void loginWrongPass()
	{
		fail("Not implemented");
	}
	
	@Test
	void createDuplicateAccount()
	{
		fail("Not implemented");
	}
	
	@Test
	void getPlayerByName()
	{
		SportsDAO db = new SportsDAO();
		assertNotNull(db.getPlayerByName("Boris Johnston"));
	}
}
