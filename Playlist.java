package models;

import java.util.ArrayList;

public class Playlist {

	private ArrayList<Playlist> play;
	
	private String ListName;
	
	public Playlist() {
		
	}
	
	public Playlist(String n, ArrayList<Playlist> play) {
		this.setListName(n);
		this.setPlay(play);
	}

	public ArrayList<Playlist> getPlay() {
		return play;
	}

	public void setPlay(ArrayList<Playlist> play) {
		this.play = play;
	}

	public String getListName() {
		return ListName;
	}

	public void setListName(String listName) {
		ListName = listName;
	}
	
	
	
}
