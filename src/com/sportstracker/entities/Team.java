package com.sportstracker.entities;

import java.util.ArrayList;

import javax.persistence.OneToMany;

public class Team
{
	@OneToMany
	// TODO map to both winner and loser columns
	private ArrayList<Match> matches;
}
