package control;

import java.util.ArrayList;

import models.Playlist;

public class PlaylistController extends ArrayList<Playlist> {

	private static final long serialVersionUID = 1L;

	private ArrayList<Playlist> listsOfPlaylists;
	private final String savePath = "//Playlists//";

	public PlaylistController() {

	}

	public ArrayList<Playlist> getListsOfPlaylists() {
		return listsOfPlaylists;
	}

	public void setListsOfPlaylists(ArrayList<Playlist> listsOfPlaylists) {
		this.listsOfPlaylists = listsOfPlaylists;
	}

	public void displayList(ArrayList<Playlist> playlists) {

	}
	
	public void removePlaylist(String name) {

	}
	
	public void savePlaylist(Playlist p) {
		String fileName = p.getListName() + ".playlist";
		System.out.println(savePath+fileName);
	}
	
	public void loadPlaylist() {
		
	}
}
