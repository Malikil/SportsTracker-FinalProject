package com.sportstracker.border;

import com.sportstracker.entities.User;

public interface IUserDatabase
{
	public User getLogin(String username, String password);
	public String createAccount(String username, String password);
}
