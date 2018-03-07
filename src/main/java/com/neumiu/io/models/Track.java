package com.neumiu.io.models;

import java.io.IOException;
import java.io.Serializable;

import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * The Track Model
 * @author NeuMiu Team
 */
public class Track implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title;
	private String genre;
	private String artist;
	private String songPath;
	private String artPath;
	private final String noArtwork = "images/NoArtwork.png";

	/**
	 * Default Track Constructor
	 */
	public Track() {}

	/**
	 * Loaded Track Constructor
	 * @param t (String)
	 * @param g (String)
	 * @param artist (String)
 	 * @param song (String)
	 * @param art (String)
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
	 * Gets the "Title" of a Track Instance
	 * @return title - Title of Track
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the "Title" of a Track Instance 
	 * @param title (String)
	 */
	public void setTitle(String title) {
		if (title == null || title.isEmpty()) {
			this.title = "Unknown";
		}
		this.title = title;
	}

	/**
	 * Gets the "Genre" of a Track Instance
	 * @return genre - Genre of Track
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Sets the "Genre" of a Track Instance
	 * @param genre (String)
	 */
	public void setGenre(String genre) {
		if (genre == null || genre.isEmpty()) {
			this.genre = "Unknown";
		}
		this.genre = genre;
	}
	
	/**
	 * Gets the "Artist" of a Track Instance
	 * @return artist - Artist of Track
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * Sets the "Artist" of a Track Instance
	 * @return artist (String)
	 */
	public void setArtist(String artist) {
		if (artist == null || artist.isEmpty()) {
			this.artist = "Unknown";
		}
		this.artist = artist;
	}

	/**
	 * Gets the "Song File Path" of a Track Instance
	 * @return songPath - Song File Path of Track
	 */
	public String getSongPath() {
		return songPath;
	}

	/**
	 * Sets the "Song File Path" of a Track Instance
	 * @return songPath (String)
	 */
	public void setSongPath(String song) {
		if (song == null || song.isEmpty()) {
			throw new IllegalArgumentException("songPath cannot be NULL/Empty");
		}
		this.songPath = song;
	}

	/**
	 * Gets the "Artwork File Path" of a Track Instance
	 * @return artPath - Artwork File Path of Track
	 */
	public String getArtwork() {
		return artPath;
	}

	/**
	 * Sets the "Artwork File Path" of a Track Instance
	 * @return artPath (String)
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
