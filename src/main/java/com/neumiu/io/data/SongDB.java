package com.neumiu.io.data;

import java.io.Serializable;
import java.util.ArrayList;

import com.neumiu.io.models.Track;

public class SongDB implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Track> songDB;
	
	public SongDB() {
		songDB = new ArrayList<>();
	}
	
	public ArrayList<Track> getSongDB() {
		return songDB;
	}
	
	public void setSongDB(ArrayList<Track> songDB) {
		this.songDB = songDB;
	}
}
