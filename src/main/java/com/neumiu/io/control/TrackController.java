package com.neumiu.io.control;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.github.trilarion.share.sampled.file.TAudioFileFormat;
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

	public String getTotalTime(File song) throws UnsupportedAudioFileException, IOException {
		String runTime = null;
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(song);
		AudioFormat format = audioInputStream.getFormat();
		if (format.toString().contains("MPEG1L3")) {
			AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(song);
		    if (fileFormat instanceof AudioFileFormat) {
		        Map<?, ?> properties = ((AudioFileFormat) fileFormat).properties();
		        String key = "duration";
		        Long microseconds = (Long) properties.get(key);
		        int mili = (int) (microseconds / 1000);
		        int sec = (mili / 1000) % 60 + 1;
		        int min = (mili / 1000) / 60;
		        String secString = String.format("%02d", sec);
		        runTime = min + ":" + secString;
		    } else {
		        throw new UnsupportedAudioFileException();
		    }
		}
		else if (format.toString().contains("FLAC")) {
			long audioFileLength = song.length();
			int frameSize = format.getFrameSize();
			float frameRate = format.getFrameRate();
			float durationInSeconds = (audioFileLength / (frameSize * frameRate));
			System.out.println(durationInSeconds);
		}
		else if (format.toString().contains("VORBIS")) {
			long audioFileLength = song.length();
			int frameSize = format.getFrameSize();
			float frameRate = format.getFrameRate();
			float durationInSeconds = (audioFileLength / (frameSize * frameRate));
		}
		else if (format.toString().contains("PCM")) {
			double durationInSeconds = (audioInputStream.getFrameLength()+0.0) / (format.getFrameSize() * format.getFrameRate());
			System.out.println(durationInSeconds);
			runTime = ""+durationInSeconds;
		}
		long frames = audioInputStream.getFrameLength();
		double durationInSeconds = (frames+0.0) / format.getFrameRate();  
//		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(song);
//		AudioFormat format = audioInputStream.getFormat();
//		long frames = audioInputStream.getFrameLength();
//		double durationInSeconds = (frames+0.0) / format.getFrameRate();
//		System.out.println(durationInSeconds);
//		long millis = (long) (durationInSeconds * 1000);
//        int sec = (int) (((millis / 1000) % 60) + 1);
//        int min = (int) ((millis / 1000) / 60);
//        String secString = String.format("%02d", sec);
//        String time = min + ":" + secString;
//        System.out.println(time);
//        return time;
		String secondsOutPut = ""+durationInSeconds;
		return runTime;
	}
	
	public long getTotalTimeMillis(File song) throws UnsupportedAudioFileException, IOException {
		long runTime = 0;
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(song);
		AudioFormat format = audioInputStream.getFormat();
		if (format.toString().contains("MPEG1L3")) {
			AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(song);
		    if (fileFormat instanceof AudioFileFormat) {
		        Map<?, ?> properties = ((AudioFileFormat) fileFormat).properties();
		        String key = "duration";
		        Long microseconds = (Long) properties.get(key);
		        int mili = (int) (microseconds / 1000);
		        runTime = mili;
		    } else {
		        throw new UnsupportedAudioFileException();
		    }
		}
		else if (format.toString().contains("FLAC")) {
			long audioFileLength = song.length();
			int frameSize = format.getFrameSize();
			float frameRate = format.getFrameRate();
			float durationInSeconds = (audioFileLength / (frameSize * frameRate));
			System.out.println(durationInSeconds);
		}
		else if (format.toString().contains("VORBIS")) {
			long audioFileLength = song.length();
			int frameSize = format.getFrameSize();
			float frameRate = format.getFrameRate();
			float durationInSeconds = (audioFileLength / (frameSize * frameRate));
		}
		else if (format.toString().contains("PCM")) {
			double durationInSeconds = (audioInputStream.getFrameLength()+0.0) / (format.getFrameSize() * format.getFrameRate());
			System.out.println(durationInSeconds);
		}
		long frames = audioInputStream.getFrameLength();
		double durationInSeconds = (frames+0.0) / format.getFrameRate();  
//		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(song);
//		AudioFormat format = audioInputStream.getFormat();
//		long frames = audioInputStream.getFrameLength();
//		double durationInSeconds = (frames+0.0) / format.getFrameRate();
//		System.out.println(durationInSeconds);
//		long millis = (long) (durationInSeconds * 1000);
//        int sec = (int) (((millis / 1000) % 60) + 1);
//        int min = (int) ((millis / 1000) / 60);
//        String secString = String.format("%02d", sec);
//        String time = min + ":" + secString;
//        System.out.println(time);
//        return time;
		return runTime;
	}
}
