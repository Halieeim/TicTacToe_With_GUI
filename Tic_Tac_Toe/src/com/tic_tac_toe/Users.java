package com.tic_tac_toe;

public class Users {
	private final String userName;
	private int score;
	private final char team;
	
	public Users(final String uname, final char team) {
		assert team == 'X' || team == 'O' : "Team Character must be 'X' or 'O'"; 
		this.userName = uname;
		this.team = team;
		this.score = 0;
	}
	
	public int getScore() {
		return this.score;
	}
	public String getName() {
		return this.userName;
	}
	public char getTeam() {
		return this.team;
	}
	public void incrementScore() {
		this.score++;
	}
}
