package com.sportstracker.border;

import com.sportstracker.entities.User;

public interface IUserDatabase
{
	public User getUser(String username);
	public String createAccount(String username, String password);
}
