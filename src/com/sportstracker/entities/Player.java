package com.sportstracker.entities;

import javax.persistence.*;

@Entity
@Table(name="Players")
public class Player
{

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="teamId")
	private Team team;
	
	private String pFirstName;
	private String pLastName;
	private String position;
	private int jerseyNumber;
	private int minutesPlayed;
	private int gamesPlayed;
	private int goals;
	private int assists;
	private int goalAttempts;
	private int age;
	private int weight;
	private int height;
	private int yellowCards;
	private int redCards;
	private int attemptedPenalties;
	private int scoredPenalties;
	private boolean activePlayer;
	
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
	public int getMinutesPlayed() {
		return minutesPlayed;
	}
	public void setMinutesPlayed(int minutesPlayed) {
		this.minutesPlayed = minutesPlayed;
	}
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	public boolean isActivePlayer() {
		return activePlayer;
	}
	public void setActivePlayer(boolean activePlayer) {
		this.activePlayer = activePlayer;
	}
	public int getGoals() {
		return goals;
	}
	public void setGoals(int goals) {
		this.goals = goals;
	}
	public int getAssists() {
		return assists;
	}
	public void setAssists(int assists) {
		this.assists = assists;
	}
	public int getGoalAttempts() {
		return goalAttempts;
	}
	public void setGoalAttempts(int goalAttempts) {
		this.goalAttempts = goalAttempts;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getYellowCards() {
		return yellowCards;
	}
	public void setYellowCards(int yellowCards) {
		this.yellowCards = yellowCards;
	}
	public int getRedCards() {
		return redCards;
	}
	public void setRedCards(int redCards) {
		this.redCards = redCards;
	}
	public int getAttemptedPenalties() {
		return attemptedPenalties;
	}
	public void setAttemptedPenalties(int attemptedPenalties) {
		this.attemptedPenalties = attemptedPenalties;
	}
	public int getScoredPenalties() {
		return scoredPenalties;
	}
	public void setScoredPenalties(int scoredPenalties) {
		this.scoredPenalties = scoredPenalties;
	}
	
}
