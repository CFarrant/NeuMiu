package com.neumiu.io.control;

import java.io.File;

import com.neumiu.io.models.Track;

import javafx.stage.FileChooser;

public class TrackController {
	
	private File musicFile = null;
	
	public TrackController() {}
	
	public Track addTrack(){
		FileChooser fc = new FileChooser();
		String path = null;
		try {
			fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3", "*.mp3"), 
					new FileChooser.ExtensionFilter("WAV", "*.wav"), 
					new FileChooser.ExtensionFilter("OGG", "*.ogg"),
					new FileChooser.ExtensionFilter("FLAC", "*.flac"));
			musicFile = fc.showOpenDialog(null);
			if (musicFile != null) {
				path = musicFile.getAbsolutePath();
				path = path.replace("\\", "/");
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Track newSong = new Track();
		newSong.setSongPath(path);
		editTrack(newSong);
		
		return newSong;
	}
	
	public void editTrack(Track track) {
		
	}
	
	public void removeTrack(Track track) {
		
	}
}
