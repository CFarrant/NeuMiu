package com.neumiu.io.data;

import java.io.Serializable;

public class DBController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private PlaylistDB allPlaylist;
	private SongDB allSong;
	
	public DBController() {
		allPlaylist = new PlaylistDB();
		allSong = new SongDB();
	}

	public PlaylistDB getAllPlaylist() {
		return allPlaylist;
	}

	public void setAllPlaylist(PlaylistDB allPlaylist) {
		this.allPlaylist = allPlaylist;
	}

	public SongDB getAllSong() {
		return allSong;
	}

	public void setAllSong(SongDB allSong) {
		this.allSong = allSong;
	}
}
