package tests;

import com.sportstracker.controller.LoginManager;
import com.sportstracker.entities.User;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProjectTests
{
	@Test
	void loginAdmin()
	{
		User user = LoginManager.tryLogin("User", "Pass");
		assertTrue(user != null && user.isAdmin());
	}
	
	@Test
	void loginBasic()
	{
		User user = LoginManager.tryLogin("Basic", "Pass");
	}

	@Test
	void loginNoUser()
	{
		
	}
	
	@Test
	void loginWrongPass()
	{
		
	}
	
	@Test
	void createDuplicateAccount()
	{
		
	}
}
