package com.sportstracker.entities;

import java.util.ArrayList;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;

public class Team
{
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="")
	})
	// TODO map to both winner and loser columns
	private ArrayList<Match> matches;
}
