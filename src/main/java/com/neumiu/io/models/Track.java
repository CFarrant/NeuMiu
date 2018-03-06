package com.neumiu.io.models;

import java.io.IOException;
import java.io.Serializable;

import javax.sound.sampled.UnsupportedAudioFileException;

public class Track implements Serializable{

	private static final long serialVersionUID = 1L;
	private String title;
	private String genre;
	private String artist;
	private String songPath;
	private String artPath;
	private final String noArtwork = "images/NoArtwork.png";

	public Track() {}

	public Track(String t, String g, String artist, String song, String art) throws UnsupportedAudioFileException, IOException {
		this.setTitle(t);
		this.setGenre(g);
		this.setArtist(artist);
		this.setSongPath(song);
		this.setArtwork(art);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null || title.isEmpty()) {
			this.title = "Unknown";
		}
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {		
		if (genre == null || genre.isEmpty()) {
			this.genre = "Unknown";
		}
		this.genre = genre;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		if (artist == null || artist.isEmpty()) {
			this.artist = "Unknown";
		}
		this.artist = artist;
	}

	public String getSongPath() {
		return songPath;
	}

	public void setSongPath(String song) {
		if (song == null || song.isEmpty()) {
			throw new IllegalArgumentException("songPath cannot be NULL/Empty");
		}
		this.songPath = song;
	}

	public String getArtwork() {
		return artPath;
	}

	public void setArtwork(String art) {
		if (art == null || art.isEmpty()) {
			this.artPath = noArtwork;
		}
		this.artPath = art;
	}
	
	@Override
	public String toString() {
		return this.getArtist()+" ~ "+this.getTitle()+" ~ "+this.getGenre();
	}
}
