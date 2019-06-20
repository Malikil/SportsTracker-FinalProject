package com.sportstracker.entities;

import javax.persistence.*;

@Entity
@Table(name="Users")
public class User
{
	@Id
	private String uname;
	private String pword;
	private boolean admin;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPword() {
		return pword;
	}
	public void setPword(String pword) {
		this.pword = pword;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
