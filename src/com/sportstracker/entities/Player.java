package com.sportstracker.entities;

import javax.persistence.*;

@Entity
@Table(name="Players")
public class Player
{

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne//(fetch=FetchType.LAZY)
	@JoinColumn(name="teamId")
	private Team team;
	
	private String pFirstName;
	private String pLastName;
	private String position;
	private int jerseyNumber;
	private int age;
	private int height;
	
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public String getPFirstName() {
		return pFirstName;
	}
	public void setPFirstName(String pFirstName) {
		this.pFirstName = pFirstName;
	}
	public String getPLastName() {
		return pLastName;
	}
	public void setPLastName(String pLastName) {
		this.pLastName = pLastName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getJerseyNumber() {
		return jerseyNumber;
	}
	public void setJerseyNumber(int jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
