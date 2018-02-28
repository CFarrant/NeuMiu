package com.neumiu.io.control;

import java.io.IOException;

import com.neumiu.io.enums.VolumeLevel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
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
	private Canvas mainCanvas;

	@FXML
	public void helpScreen(ActionEvent f) throws IOException {

		Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/helpWindow.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("NeuMiu Help");

		Text t = new Text("lol no help for you");
		// t.setText("lol no help for you");
		t.setFont(Font.font("Verdana", 20));
		t.setFill(Color.BLUEVIOLET);
		t.setTextAlignment(TextAlignment.CENTER);
		stage.show();
		// showAlert("How to use NueMiu", null, "The Halp");

	}

	private boolean mute;
	private VolumeLevel volume = VolumeLevel.MEDIUM;

	private void showAlert(String title, String header, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);
		alert.showAndWait();
	}

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
		currentlyPlaying.setText(artist + " ~ " + title);
	}

	public void updateCurTime(String currentTime) {
		curTime.setText(currentTime);
	}

	public void updateTotalTime(String fullTime) {
		totalTime.setText(fullTime);
	}
}
