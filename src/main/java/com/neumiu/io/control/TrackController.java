package com.neumiu.io.control;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.neumiu.io.models.Track;

public class TrackController {
	
	public TrackController() {}
	
	public void addTrack(Track track){

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
		        int sec = (mili / 1000) % 60;
		        int min = (mili / 1000) / 60;
		        String secString = String.format("%02d", sec);
		        runTime = min + ":" + secString;
		    } else {
		        throw new UnsupportedAudioFileException();
		    }
		}
		else if (format.toString().contains("FLAC")) {
//			double bps = format.getSampleSizeInBits();
//			double sr = format.getSampleRate();
//			double c = format.getChannels();
//			long fs = song.length();
//			double duration = (8*(fs)/(bps*sr*c));
//			System.out.println("TEST VALUE: "+duration);
//			int sec = (int) (duration % 60);
//			int min = (int) (duration / 60);
//	        String secString = String.format("%02d", sec);
//	        runTime = min + ":" + secString;
//	        System.out.println("TEST VALUE: "+runTime);
		}
		else if (format.toString().contains("VORBIS")) {
			AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(song);
		    if (fileFormat instanceof AudioFileFormat) {
		        Map<?, ?> properties = ((AudioFileFormat) fileFormat).properties();
		        String key = "duration";
		        Long microseconds = (Long) properties.get(key);
		        int mili = (int) (microseconds / 1000);
		        int sec = (mili / 1000) % 60;
		        int min = (mili / 1000) / 60;
		        String secString = String.format("%02d", sec);
		        runTime = min + ":" + secString;
		    } else {
		        throw new UnsupportedAudioFileException();
		    }
		}
		else if (format.toString().contains("PCM")) {
			double length = song.length();
			double sampleRate = format.getSampleRate();
			double channels = format.getChannels();
			double audioSampleSize = format.getSampleSizeInBits();
			System.out.println("Lenght: "+length+", Sample Rate: "+sampleRate+", Channels: "+channels+", Audio Sample Size in Bits: "+audioSampleSize);
			double time = length / (sampleRate * channels * (audioSampleSize / 8));
			int timeInSec = (int)time;
			int sec = timeInSec % 60;
	        int min = timeInSec / 60;
	        String secString = String.format("%02d", sec);
	        runTime = min + ":" + secString;
		}
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
//			double bps = format.getSampleSizeInBits();
//			double sr = format.getSampleRate();
//			double c = format.getChannels();
//			long fs = song.length();
//			double duration = (8*(fs)/(bps*sr*c));
//			long mili = (long) (duration * 1000);
//	        runTime = mili;
//	        System.out.println("TEST VALUE: "+mili);
		}
		else if (format.toString().contains("VORBIS")) {
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
		else if (format.toString().contains("PCM")) {
			double length = song.length();
			double sampleRate = format.getSampleRate();
			double channels = format.getChannels();
			double audioSampleSize = format.getSampleSizeInBits();
			double time = length / (sampleRate * channels * (audioSampleSize / 8));
			runTime = (long)(time * 1000);
		}
		return runTime;
	}
}
