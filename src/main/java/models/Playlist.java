package models;

import java.util.ArrayList;

public class Playlist {

	private ArrayList<Track> play;
	
	private String ListName;
	
	public Playlist() {
		
	}
	
	public Playlist(String n, ArrayList<Track> play) {
		this.setListName(n);
		this.setPlay(play);
	}

	public ArrayList<Track> getPlay() {
		return play;
	}

	public void setPlay(ArrayList<Track> play) {
		this.play = play;
	}

	public String getListName() {
		return ListName;
	}

	public void setListName(String listName) {
		ListName = listName;
	}
	
	
	
}
