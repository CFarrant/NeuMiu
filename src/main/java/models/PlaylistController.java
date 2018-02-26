package models;

import java.util.ArrayList;

public class PlaylistController extends ArrayList<Playlist> {

	private static final long serialVersionUID = 1L;

	private ArrayList<Playlist> listsOfPlaylists;

	public PlaylistController() {

	}

	public ArrayList<Playlist> getListsOfPlaylists() {
		return listsOfPlaylists;
	}

	public void setListsOfPlaylists(ArrayList<Playlist> listsOfPlaylists) {
		this.listsOfPlaylists = listsOfPlaylists;
	}

	public void displayList(ArrayList<Playlist> f) {

	}
	
	public void removePlaylist(String name) {

}

}
