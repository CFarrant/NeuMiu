package com.neumiu.io.control;

import java.io.IOException;

import com.neumiu.io.enums.VolumeLevel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

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

	@FXML
	private TextFlow aboutHelp;

	@FXML
	public void helpScreen(MouseEvent f) throws IOException {
		Parent root2 = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/helpWindow.fxml"));
		Stage s = new Stage();
		Scene scene = new Scene(root2);
		s.setScene(scene);
		s.setTitle("Help");
		s.show();
		// Text t = new Text();
		// t.setText("This is a text sample");
		// t.setFont(Font.font ("Verdana", 20));
		// t.setFill(Color.RED);

	}

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

	}

	public void nextSong() {

	}

	public void prevSong() {

	}

	public void pause() {

	}

	public void stop() {

	}

}
