package com.neumiu.io.data;

import java.io.Serializable;
import java.util.ArrayList;

import com.neumiu.io.models.Track;
/**
 * 
 * @author Chris
 *
 */
public class SongDB implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Track> songDB;

	/**
	 * SongDB Constructor
	 */
	public SongDB() {
		songDB = new ArrayList<>();
	}

	/**
	 * gets the songs
	 * 
	 * @return
	 */
	public ArrayList<Track> getSongDB() {
		return songDB;
	}

	/**
	 * sets the songs
	 * 
	 * @param songDB
	 */
	public void setSongDB(ArrayList<Track> songDB) {
		this.songDB = songDB;
	}
}
