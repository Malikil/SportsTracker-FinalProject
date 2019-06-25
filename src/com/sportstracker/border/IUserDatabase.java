package com.sportstracker.border;

import com.sportstracker.entities.User;

public interface IUserDatabase
{
	public User getLogin(String username, String password);
	public Integer createAccount(String username, String password);
}
