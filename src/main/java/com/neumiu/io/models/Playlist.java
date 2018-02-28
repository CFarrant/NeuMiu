package com.neumiu.io.models;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Playlist {

	private ArrayList<Track> play;
	private String listName;
	private static int untitledCount = 1;
	
	public Playlist() {}
	
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
		return listName;
	}

	public void setListName(String listName) {
		if (listName == null || listName.isEmpty()) {
			if (untitledCount == 0) {
				this.listName = ("Untitled");
			}
			else if (untitledCount > 0) {
				this.listName = ("Untitled"+untitledCount);	
			}
			untitledCount++;
		}
		this.listName = listName;
	}
}
