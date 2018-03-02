package com.neumiu.io.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import javax.sound.sampled.UnsupportedAudioFileException;

import com.neumiu.io.enums.VolumeLevel;
import com.neumiu.io.utils.ApplicationData;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.goxr3plus.javastreamplayer.stream.Status;
import main.java.goxr3plus.javastreamplayer.stream.StreamPlayer;
import main.java.goxr3plus.javastreamplayer.stream.StreamPlayerEvent;
import main.java.goxr3plus.javastreamplayer.stream.StreamPlayerException;
import main.java.goxr3plus.javastreamplayer.stream.StreamPlayerListener;

public class FXMLController extends StreamPlayer implements StreamPlayerListener {

	private PlaylistController playlist;
	private TrackController track;
	private ApplicationData appData;
	private String fileName = "NeuMiu.db";
	private boolean mute;
	private VolumeLevel volume = VolumeLevel.MEDIUM;

	@FXML
	private TreeView<?> playlistTree;

	@FXML
	private ListView<?> songInPlaylist;

	@FXML
	private Slider volumeSlider, seekBar;

	@FXML
	private CheckBox muteBox, shuffleBox;

	@FXML
	private ImageView coverArt;

	@FXML
	private TextField currentlyPlaying;

	@FXML
	private Label curTime, totalTime;

	@FXML
	private Button prevSong, stopSong, pauseSong, playSong, nextSong;

	@FXML
	private Canvas mainCanvas;

	@FXML
	public void helpScreen(ActionEvent f) throws IOException {

		Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/helpWindow.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("NeuMiu Help");
		stage.show();
	}

	@FXML
	public void browse(ActionEvent r) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/helpWindow.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Change Cover Art");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png"));
		fileChooser.showOpenDialog(stage);
	}

	@FXML
	public void editPlaylist() throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/PlaylistWindow.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Edit Playlist");
		stage.show();
	}

	@FXML
	public void addSongs(ActionEvent g) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/AddSongs.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Add Songs to Playlist");
		stage.show();
	}

	@FXML
	public void removeSongs(ActionEvent h) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/RemoveSongs.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Remove Songs From Playlist");
		stage.show();
	}

	@FXML
	private Button cancel;

	@FXML
	public void closeButton(ActionEvent h) {
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}

	public void editSong(ActionEvent q) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/EditSong.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Edit Song");
		stage.show();
	}

	public FXMLController() {
		playlist = new PlaylistController();
		track = new TrackController();
		addStreamPlayerListener((StreamPlayerListener) this);
		try {
			this.loadApplicationData();
		} catch (ClassNotFoundException | IOException e) {
			appData = new ApplicationData();
		}
	}

	@FXML
	public void addSong(ActionEvent k) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/helpWindow.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Add Song");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3", "*.mp3"),
				new FileChooser.ExtensionFilter("OGG", "*.ogg"), new FileChooser.ExtensionFilter("WAV", "*.wav"),
				new FileChooser.ExtensionFilter("FLAC", "*.flac"));
		fileChooser.showOpenDialog(stage);
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

	private void manipulateSeekBar(long totalTime, long currentTime) {
		seekBar.setMax(totalTime);
		seekBar.setMin(0);
		seekBar.setValue(currentTime);
	}
	
	private String time = null;
	
	private void milliToString(long value) {
		int mili = (int)(value / 1000);
        int sec = (mili / 1000) % 60;
        int min = (mili / 1000) / 60;
        String secString = String.format("%02d", sec);
        time = min + ":" + secString;
        curTime.setText(time);
	}

	private long totalPlayTime = 0;

	public void playSong(ActionEvent pla) {
		if (this.getStatus() != Status.PLAYING && this.getStatus() != Status.PAUSED) {
			resetPlayer();
			try {
				File song = new File("sample/song/test.flac").getAbsoluteFile();
				try {
					totalTime.setText(track.getTotalTime(song));
				} catch (UnsupportedAudioFileException | IOException e) {
					e.printStackTrace();
				}
				try {
					totalPlayTime = track.getTotalTimeMillis(song);
				} catch (UnsupportedAudioFileException | IOException e) {
					e.printStackTrace();
				}
				open(song);
				play();
				
			} catch (StreamPlayerException e) {
				e.printStackTrace();
			}
		}
		else if (this.getStatus() == Status.PLAYING) {
			pause();
		}
		else if (this.getStatus() == Status.PAUSED) {
			resume();
		}
	}

	public void createSong() {
		appData.getTracks().add(track.addTrack());
	}

	public void nextSong(ActionEvent next) {

	}

	public void prevSong(ActionEvent prev) {
		
	}
    
//	public void pauseSong(ActionEvent pau) throws InterruptedException {
//		if (this.getStatus() == Status.PLAYING) {
//			pause();
//		}
//		else if (this.getStatus() == Status.PAUSED) {
//			resume();
//		}
//	}

	public void stopSong(ActionEvent sto) throws InterruptedException {
		if (this.getStatus() == Status.PAUSED || this.getStatus() == Status.PLAYING) {
			stop();
		}
	}

	private void resetPlayer() {
		manipulateSeekBar(100, 0);
		curTime.setText("0:00");
		totalTime.setText("0:00");
	}

	public void updateArtwork(String path) {
		coverArt.setImage(new Image(path));
	}

	public void updatePlaying(String artist, String title) {
		currentlyPlaying.setText(artist + " ~ " + title);
	}

	public void updateCurTime(String currentPlayTime) {
		curTime.setText(currentPlayTime);
	}

	public void updateTotalTime(String fullTime) {
		totalTime.setText(fullTime);
	}

	private void saveApplicationData() throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
			out.writeObject(this.appData);
		}
	}

	public ApplicationData loadApplicationData() throws ClassNotFoundException, IOException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
			this.appData = (ApplicationData) in.readObject();
		}
		return appData;
	}

	@Override
	public void opened(Object arg0, Map<String, Object> arg1) {
	}

	private long currentPlayTimeMillis;

	@Override
	public void progress( int arg0, long arg1, byte[] arg2, Map<String, Object> arg3) {
		final long temp = arg1;
		currentPlayTimeMillis = arg1/1000;
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				milliToString(temp);
				manipulateSeekBar(totalPlayTime, currentPlayTimeMillis);
			}
		});
	}

	@Override
	public void statusUpdated(StreamPlayerEvent arg0) {
		System.out.println(arg0.getPlayerStatus());
	}

	public void exit(WindowEvent event) {
		stop();
		System.exit(0);
	}
}
