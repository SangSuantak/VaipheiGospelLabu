package com.vaiphei.gospellabu;

public class SongFirstChar {
	private int SongBookId;
	private String SongFirstChar;
	private int SongCount;
	
	public int getSongBookId() {
		return SongBookId;
	}
	
	public void setSongBookId(int SongBookId) {
		this.SongBookId = SongBookId;
	}
	
	public String getSongFirstChar() {
		return SongFirstChar;
	}
	
	public void setSongFirstChar(String SongFirstChar) {
		this.SongFirstChar = SongFirstChar;
	}

	public int getSongCount() {
		return SongCount;
	}

	public void setSongCount(int songCount) {
		SongCount = songCount;
	}
	
	public String toString() {
		return SongFirstChar + " (" + Integer.toString(SongCount) + ")";
	}
	
}