package com.sportstracker.entities;

import java.util.ArrayList;

import javax.persistence.*;

@Entity
@Table(name="Users")
public class User
{
	@Id
	private String username;
	private String password;
	private boolean admin;
	private ArrayList<String> favourites = new ArrayList<String>();
	
	public User() { this(null, null); }
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
		admin = false;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String uname) {
		this.username = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String pword) {
		this.password = pword;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public ArrayList<String> getFavourites()
	{
		return favourites;
	}
	public void setFavourite(ArrayList<String> favs)
	{
		favourites = favs;
	}
}
