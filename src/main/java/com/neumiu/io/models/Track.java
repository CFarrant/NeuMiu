package com.neumiu.io.models;

import java.io.IOException;
import java.io.Serializable;

import javax.sound.sampled.UnsupportedAudioFileException;

public class Track implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title;
	private String genre;
	private String artist;
	private String songPath;
	private String artPath;
	private final String noArtwork = "images/NoArtwork.png";

	/**
	 * Track Constructor
	 */
	public Track() {
	}

	/**
	 * Overidden track constructor
	 * 
	 * @param t
	 * @param g
	 * @param artist
	 * @param song
	 * @param art
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 */
	public Track(String t, String g, String artist, String song, String art)
			throws UnsupportedAudioFileException, IOException {
		this.setTitle(t);
		this.setGenre(g);
		this.setArtist(artist);
		this.setSongPath(song);
		this.setArtwork(art);
	}

	/**
	 * gets the Track title
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * sets the track title
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		if (title == null || title.isEmpty()) {
			this.title = "Unknown";
		}
		this.title = title;
	}

	/**
	 * gets the genre
	 * 
	 * @return
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * sats the genre
	 * 
	 * @param genre
	 */
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

	/**
	 * gets the path of the song
	 * 
	 * @return
	 */
	public String getSongPath() {
		return songPath;
	}

	/**
	 * sets the path of the song
	 * 
	 * @param song
	 */
	public void setSongPath(String song) {
		if (song == null || song.isEmpty()) {
			throw new IllegalArgumentException("songPath cannot be NULL/Empty");
		}
		this.songPath = song;
	}

	/**
	 * gets the artwork path
	 * 
	 * @return
	 */
	public String getArtwork() {
		return artPath;
	}

	/**
	 * sets the artwork path
	 * 
	 * @param art
	 */
	public void setArtwork(String art) {
		if (art == null || art.isEmpty()) {
			this.artPath = noArtwork;
		}
		this.artPath = art;
	}

	@Override
	public String toString() {
		return this.getArtist() + " ~ " + this.getTitle() + " ~ " + this.getGenre();
	}
}
