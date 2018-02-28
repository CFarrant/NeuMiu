package com.neumiu.io.utils;

import java.util.ArrayList;

import com.neumiu.io.models.Playlist;
import com.neumiu.io.models.Track;

public class ApplicationData {

	private ArrayList<Track> allSongs;
	private ArrayList<Playlist> playlists;
	
	public ApplicationData() {
		allSongs = new ArrayList<>();
		playlists = new ArrayList<>();
	}

	public ArrayList<Track> getTracks() {
		return allSongs;
	}

	public void setTracks(ArrayList<Track> tracks) {
		this.allSongs = tracks;
	}

	public ArrayList<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(ArrayList<Playlist> playlists) {
		this.playlists = playlists;
	}
}
