package com.neumiu.io.models;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.tritonus.share.sampled.file.TAudioFileFormat;

public class Track implements Serializable{

	private static final long serialVersionUID = 1L;
	private String title;
	private String genre;
	private String artist;
	private String totalTime;
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
		this.setTotalTime(calcTotalTime());
	}
	
	private String calcTotalTime() throws UnsupportedAudioFileException, IOException {
		String time = null;
		File file = new File(getSongPath());
		AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
	    if (fileFormat instanceof TAudioFileFormat) {
	        Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
	        String key = "duration";
	        Long microseconds = (Long) properties.get(key);
	        int mili = (int) (microseconds / 1000);
	        int sec = (mili / 1000) % 60;
	        int min = (mili / 1000) / 60;
	        time = min + ":" + sec;
	    } else {
	        throw new UnsupportedAudioFileException();
	    }
		return time;
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

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
}
