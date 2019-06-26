package com.sportstracker.entities;

import javax.persistence.*;

@Entity
//@Table(name="Users")
public class User
{
	@Id
	private String username;
	private String password;
	private boolean admin;
	
	public User() { this(null, null); }
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
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
}
