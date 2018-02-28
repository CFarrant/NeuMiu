package com.neumiu.io.models;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.tritonus.share.sampled.file.TAudioFileFormat;

public class Track {

	private String title;
	private String genre;
	private String artist;
	private String totalTime;
	private File song;
	private File artwork;
	private final File noArtwork = new File(getClass().getClassLoader().getResource("images/NoArtwork.png").getFile());

	public Track() {}

	public Track(String t, String g, String artist, File song, File art) throws IOException, UnsupportedAudioFileException {
		this.setTitle(t);
		this.setGenre(g);
		this.setArtist(artist);
		this.setSongPath(song);
		this.setArtwork(art);
		this.setTotalTime(calcTotalTime());
	}
	
	private String calcTotalTime() throws UnsupportedAudioFileException, IOException {
		String totalRunTime = null;
		AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(this.song);
		if (fileFormat instanceof TAudioFileFormat) {
			Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
		    String key = "duration";
		    Long microseconds = (Long) properties.get(key);
		    int mili = (int) (microseconds / 1000);
		    int sec = (mili / 1000) % 60;
		    int min = (mili / 1000) / 60;
		    totalRunTime = ("Runtime: " + min + ":" + sec);
		} else {
		    throw new UnsupportedAudioFileException();
		}
		return totalRunTime;
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

	public File getSongPath() {
		return song;
	}

	public void setSongPath(File song) throws IOException {
		if (song == null) {
			throw new IOException("File was unable to be found!");
		}
		this.song = song;
	}

	public File getArtwork() {
		return artwork;
	}

	public void setArtwork(File art) {
		if (art == null) {
			this.artwork = noArtwork;
		}
		this.artwork = art;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
}
