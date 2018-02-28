package com.neumiu.io.control;

import java.util.ArrayList;

import com.neumiu.io.models.Playlist;

public class PlaylistController extends ArrayList<Playlist> {

	private static final long serialVersionUID = 1L;

	private ArrayList<Playlist> listsOfPlaylists;

	public PlaylistController() {}

	public ArrayList<Playlist> getListsOfPlaylists() {
		return listsOfPlaylists;
	}

	public void setListsOfPlaylists(ArrayList<Playlist> listsOfPlaylists) {
		this.listsOfPlaylists = listsOfPlaylists;
	}

	public void displayList(ArrayList<Playlist> playlists) {

	}
	
	public void addPlaylist() {

	}
	
	public void editPlaylist() {

	}
	
	public void removePlaylist() {

	}
}
