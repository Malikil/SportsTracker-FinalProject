package com.sportstracker.border;

import com.sportstracker.entities.User;

/**
 * @deprecated Abandoned after iteration 1. Use SportsDAO on its own instead
 */
public interface IUserDatabase
{
	public User getUser(String username);
	public String createAccount(String username, String password);
}
