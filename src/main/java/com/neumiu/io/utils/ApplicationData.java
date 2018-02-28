package com.neumiu.io.utils;

import java.util.ArrayList;

import com.neumiu.io.models.Playlist;
import com.neumiu.io.models.Track;

public class ApplicationData {

	private ArrayList<Track> tracks;
	private ArrayList<Playlist> playlists;
	
	public ApplicationData() {
		tracks = new ArrayList<>();
		playlists = new ArrayList<>();
	}

	public ArrayList<Track> getTracks() {
		return tracks;
	}

	public void setTracks(ArrayList<Track> tracks) {
		this.tracks = tracks;
	}

	public ArrayList<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(ArrayList<Playlist> playlists) {
		this.playlists = playlists;
	}
}
