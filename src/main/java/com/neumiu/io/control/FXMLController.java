package com.neumiu.io.control;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import com.neumiu.io.enums.VolumeLevel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FXMLController {

	@FXML
	private TreeView<?> playlistTree;
	
	@FXML
	private ListView<?> songInPlaylist;
	
	@FXML
	private Slider volumeSlider;
	
	@FXML
	private CheckBox muteBox;
	
	@FXML
	private CheckBox shuffleBox;
	
	@FXML
	private ImageView coverArt;
	
	@FXML
	private TextField currentlyPlaying;
	
	@FXML
	private Slider seekBar;
	
	@FXML
	private Label curTime;
	
	@FXML
	private Label totalTime;
	
	@FXML
	private Button prevSong;
	
	@FXML
	private Button stopSong;
	
	@FXML
	private Button pauseSong;
	
	@FXML
	private Button playSong;
	
	@FXML
	private Button nextSong;
	
	private boolean mute;
	private VolumeLevel volume = VolumeLevel.MEDIUM;

	public FXMLController() {

	}

	public void run() {

	}

	public boolean isMute() {
		return mute;
	}

	public void setMute(boolean mute) {
		this.mute = mute;
	}

	public VolumeLevel getVolume() {
		return volume;
	}

	public void setVolume(VolumeLevel volume) {
		this.volume = volume;
	}

	public void shuffel() {

	}

	public int volume(int vol) {
		return vol;
	}

	public void mute(boolean mute) {
		if (mute == true) {

		} else {

		}
	}
	
	public void play() {
		playSong.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				String path = "images/e1m1.jpg";
				String artist = "Unknown";
				String title = "Gates to Hell, E1M1";
				String fullPlayTime = "3:59";
				String currentTime = "0:00";
				updateArtwork(path);
				updatePlaying(artist, title);
				updateTotalTime(fullPlayTime);
				updateCurTime(currentTime);
			}
		});
	}

	public void nextSong() {

	}

	public void prevSong() {

	}

	public void pause() {

	}

	public void stop() {

	}
	
	public void updateArtwork(String path) {
		coverArt.setImage(new Image(path));
	}
	
	public void updatePlaying(String artist, String title) {
		currentlyPlaying.setText(artist+" ~ "+title);
	}
	
	public void updateCurTime(String currentTime) {
		curTime.setText(currentTime);
	}
	
	public void updateTotalTime(String fullTime) {
		totalTime.setText(fullTime);
	}
}
