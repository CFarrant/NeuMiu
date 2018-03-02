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
import javafx.event.EventHandler;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.goxr3plus.javastreamplayer.stream.StreamPlayer;
import main.java.goxr3plus.javastreamplayer.stream.StreamPlayerEvent;
import main.java.goxr3plus.javastreamplayer.stream.StreamPlayerException;
import main.java.goxr3plus.javastreamplayer.stream.StreamPlayerListener;

public class FXMLController extends StreamPlayer implements StreamPlayerListener{

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
	private TextFlow aboutHelp, tofl;

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
		t.setText("How do Use");
		// t.setFont(Font.font("Verdana", 20));
		t.setFill(Color.BLUEVIOLET);
		t.setTextAlignment(TextAlignment.CENTER);

		stage.show();
		tofl = new TextFlow(t);
		tofl.getChildren().add(t);

	}
	
	@FXML
	public void browse(ActionEvent r) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		//fileChooser.showOpenDialog(stage);
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
	
	private void manipulateSeekBar(long totalTime, long currentTime) {
		seekBar.setMax(totalTime);
		seekBar.setMin(0);
		seekBar.setValue(currentTime);
	}
	
	private String time;
	
	private String milliToString(long value) {
		int mili = (int)(value / 1000);
        int sec = ((mili / 1000) % 60) + 1;
        int min = (mili / 1000) / 60;
        String secString = String.format("%02d", sec);
        time = min + ":" + secString;
        return time;
	}
	
	private long totalPlayTime = 0;
	
	public void playSong() throws StreamPlayerException {
		playSong.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					File song = new File("sample/song/test.flac").getAbsoluteFile();
					try {
						totalTime.setText(track.getTotalTime(song));
					} catch (UnsupportedAudioFileException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						totalPlayTime = track.getTotalTimeMillis(song);
					} catch (UnsupportedAudioFileException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					open(song);
					play();
					
				} catch (StreamPlayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
	}

	public void createSong() {
		appData.getTracks().add(track.addTrack());
	}

	public void nextSong() {

	}

	public void prevSong() {

	}

	public void pauseSong() {

	}

	public void stopSong() {
		playSong.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				stop();
			}
		});
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
	public void opened(Object arg0, Map<String, Object> arg1) {}

	private long currentPlayTimeMillis;
	
	@Override
	public void progress(int arg0, long arg1, byte[] arg2, Map<String, Object> arg3) {
		milliToString(arg1);
		currentPlayTimeMillis = arg1/1000;
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				manipulateSeekBar(totalPlayTime, currentPlayTimeMillis);
				seekBar.setValue(currentPlayTimeMillis);
				curTime.setText(time);
			}
		});
	}

	@Override
	public void statusUpdated(StreamPlayerEvent arg0) {
		System.out.println(arg0.getPlayerStatus());
	}
}
