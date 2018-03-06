package com.neumiu.io.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sound.sampled.UnsupportedAudioFileException;

import com.neumiu.io.models.Track;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.goxr3plus.javastreamplayer.stream.Status;
import main.java.goxr3plus.javastreamplayer.stream.StreamPlayer;
import main.java.goxr3plus.javastreamplayer.stream.StreamPlayerEvent;
import main.java.goxr3plus.javastreamplayer.stream.StreamPlayerException;
import main.java.goxr3plus.javastreamplayer.stream.StreamPlayerListener;

public class FXMLController extends StreamPlayer implements Initializable, StreamPlayerListener {

	// Controllers
	private TrackController track;

	// Savable Data
	private static List<Track> addedSongs;

	// ListView Controls
	private static ObservableList<Track> songs;

	// Variables
	private double volumeLevel = 50;
	private String fileName = "NeuMiu.db";
	private boolean mute;
	private String time = null;
	private long currentPlayTime;
	private long totalPlayTime = 0;
	private static int currentItem = 0;

	// Temp Song Variables
	private static String newSong = null;
	private static String title = null;
	private static String genre = null;
	private static String artist = null;
	private static String artwork = null;

	// GUI Elements
	@FXML
	private TableView<Track> songInPlaylist;
	@FXML
	private TableColumn<Track, String> titleValue, artistValue, genreValue;
	@FXML
	public Slider volumeSlider;
	@FXML
	private Slider seekBar;
	@FXML
	private CheckBox muteBox, shuffleBox;
	@FXML
	private ImageView coverArt, playTrack;
	@FXML
	private TextField currentlyPlaying, addSongName, addSongGenre, addSongArtist, editSongName, editSongGenre,
			editSongArtist;
	@FXML
	private Label curTime, totalTime;
	@FXML
	private Button cancel, save;

	/**
	 * constructor that loads songs into player
	 */
	public FXMLController() {
		track = new TrackController();
		addStreamPlayerListener((StreamPlayerListener) this);
		try {
			this.load();
		} catch (ClassNotFoundException | IOException e) {
			addedSongs = new ArrayList<>();
		}
		songs = FXCollections.observableArrayList(addedSongs);
		songInPlaylist = new TableView<Track>();
	}

	/**
	 * gets the information from the song
	 * 
	 * @param song
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	protected void getSongInfo(Track song) throws ClassNotFoundException, IOException {
		try {
			title = song.getTitle();
			newSong = song.getSongPath();
			artwork = song.getArtwork();
			artist = song.getArtist();
		} catch (NullPointerException e) {
			showAlert("Warning", null, "There were no songs to get information from!", AlertType.WARNING);
		}
	}

	@FXML
	private void refresh(MouseEvent e) {
		if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
			try {
				getSongInfo(songInPlaylist.getSelectionModel().getSelectedItem());
				currentItem = songInPlaylist.getSelectionModel().getSelectedIndex();
			} catch (ClassNotFoundException | IOException ex) {
				showAlert("Warning", null, "There were no songs to get information from!", AlertType.WARNING);
			}
		}
	}

	private void showAlert(String title, String header, String text, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);
		alert.showAndWait();
	}

	@FXML
	private void helpScreen(ActionEvent f) throws IOException {
		Parent root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/HelpWindow.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("NeuMiu Help");
		stage.show();
	}

	@FXML
	private void browseImage(ActionEvent r) throws IOException {
		Parent root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/HelpWindow.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Change Cover Art");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png"));
		File art = fileChooser.showOpenDialog(stage);
		if (artwork != null) {
			artwork = art.getPath();
		}
	}

	@FXML
	private void browseMusic(ActionEvent r) throws IOException {
		Parent root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/HelpWindow.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Add Song");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3", "*.mp3"),
				new FileChooser.ExtensionFilter("OGG", "*.ogg"), new FileChooser.ExtensionFilter("WAV", "*.wav"),
				new FileChooser.ExtensionFilter("FLAC", "*.flac"));
		File song = fileChooser.showOpenDialog(stage);
		newSong = song.getPath();
	}

	@FXML
	private void closeButton(ActionEvent h) {
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void editSong(ActionEvent q) throws IOException {
		Parent root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/EditSong.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Edit Song");
		stage.show();
	}

	@FXML
	private void removeSong(ActionEvent rms) {
		try {
			addedSongs.remove(currentItem);
		} catch (IndexOutOfBoundsException ex) {
			showAlert("Warning", null, "There are no songs to remove!", AlertType.WARNING);
		}
	}

	@FXML
	private void addSong(ActionEvent k) throws IOException {
		System.out.println("TEST");
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/AddSong.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Add New Song");
		stage.show();

	}

	public void saveNewSong(ActionEvent sNS) throws UnsupportedAudioFileException, IOException {
		title = addSongName.getText();
		artist = addSongArtist.getText();
		genre = addSongGenre.getText();
		addedSongs.add(new Track(title, genre, artist, newSong, artwork));
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}

	/**
	 * Saves added and edited song info in the player
	 * 
	 * @param snsn
	 */
	public void saveEditedSong(ActionEvent snsn) {
		Track temp = addedSongs.get(currentItem);
		addedSongs.remove(temp);
		if (!(temp.getTitle() == editSongName.getText()) || !editSongName.getText().isEmpty()) {
			temp.setTitle(addSongName.getText());
		}
		if (!(temp.getTitle() == editSongName.getText()) || !editSongArtist.getText().isEmpty()) {
			temp.setArtist(addSongArtist.getText());
		}
		if (!(temp.getTitle() == editSongName.getText()) || !editSongGenre.getText().isEmpty()) {
			temp.setGenre(addSongGenre.getText());
		}
		if (!(temp.getArtwork() == artwork)) {
			if (artwork == null) {
				temp.setArtwork("images/NoArtwork.png");
			} else {
				temp.setArtwork(artwork);
			}
		}
		addedSongs.add(temp);
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
		;
	}

	/**
	 * mutes the volume
	 * 
	 * @param m
	 */
	public void mute(ActionEvent m) {
		if (mute == true) {
			setMute(false);
			mute = false;
		} else {
			setMute(true);
			mute = true;
			muteBox.setSelected(mute);
		}
	}

	private void manipulateSeekBar(long totalTime, long currentTime) {
		seekBar.setMax(totalTime);
		seekBar.setMin(0);
		seekBar.setValue(currentTime);
	}

	private void milliToString(long value) {
		int mili = (int) (value / 1000);
		int sec = (mili / 1000) % 60;
		int min = (mili / 1000) / 60;
		String secString = String.format("%02d", sec);
		time = min + ":" + secString;
		curTime.setText(time);
	}

	/**
	 * Plays the music in the music player
	 * 
	 * @param pla
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void playSong(MouseEvent pla) throws ClassNotFoundException, IOException {
		if (this.getStatus() != Status.PLAYING && this.getStatus() != Status.PAUSED) {
			if (!totalTime.getText().equals("0:00") || !curTime.getText().equals("0:00")) {
				resetPlayer();
			}
			System.out.println(addedSongs.size());
			File song = null;
			File art = null;
			try {
				song = new File(newSong);
				art = new File(artwork);
			} catch (NullPointerException e) {
				if (song == null) {
					showAlert("Error", null, "There is no song selected to be played!", AlertType.ERROR);
				}
			}
			if (song != null) {
				try {
					totalTime.setText(track.getTotalTime(song));
					totalPlayTime = track.getTotalTimeMillis(song);
				} catch (UnsupportedAudioFileException | IOException e) {
					showAlert("Warning", null, "The song failed to calculate the total play time!", AlertType.WARNING);
				}
				currentlyPlaying.setAlignment(Pos.CENTER);
				currentlyPlaying.setText(artist + " ~ " + title);
				try {
					coverArt.setImage(new Image(new FileInputStream(art)));
				} catch (NullPointerException e) {
					coverArt.setImage(new Image("images/NoArtwork.png"));
				}
				try {
					open(song);
					play();
				} catch (StreamPlayerException e) {
					e.printStackTrace();
				}
				playTrack.setImage(new Image("icons/pause.png"));
			}
		} else if (this.getStatus() == Status.PLAYING) {
			pause();
			playTrack.setImage(new Image("icons/play.png"));
		} else if (this.getStatus() == Status.PAUSED) {
			resume();
			playTrack.setImage(new Image("icons/pause.png"));
		}
	}

	public void nextSong(MouseEvent next) throws ClassNotFoundException, IOException {
		int value = 0;
		if (currentItem < addedSongs.size() - 1) {
			stop();
			value = currentItem + 1;
			currentItem++;
			Track temp = addedSongs.get(value);
			newSong = temp.getSongPath();
			artwork = temp.getArtwork();
			artist = temp.getArtist();
			genre = temp.getGenre();
			title = temp.getTitle();
			playSong(next);
		} else {
			showAlert("Information", null, "You have reached the end of the song list!", AlertType.INFORMATION);
		}
	}

	/**
	 * goes one song back in the list og songs and plays it
	 * 
	 * @param prev
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void prevSong(MouseEvent prev) throws ClassNotFoundException, IOException {
		int value = 0;
		if (currentItem > 0 && currentItem != 0) {
			stop();
			value = currentItem - 1;
			currentItem--;
			Track temp = addedSongs.get(value);
			newSong = temp.getSongPath();
			artwork = temp.getArtwork();
			artist = temp.getArtist();
			genre = temp.getGenre();
			title = temp.getTitle();
			playSong(prev);
		} else {
			showAlert("Information", null, "You have reached the end of the song list!", AlertType.INFORMATION);
		}
	}

	/**
	 * goes one song forward and plays it
	 * 
	 * @param sto
	 * @throws InterruptedException
	 */
	public void stopSong(MouseEvent sto) throws InterruptedException {
		if (this.getStatus() == Status.PLAYING || this.getStatus() == Status.PAUSED) {
			stop();
			playTrack.setImage(new Image("icons/play.png"));
			coverArt.setImage(new Image("images/NoArtwork.png"));
			currentlyPlaying.setText("");
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

	/**
	 * updates the playlist when songs are edited or added
	 * 
	 * @param artist
	 * @param title
	 */
	public void updatePlaying(String artist, String title) {
		currentlyPlaying.setText(artist + " ~ " + title);
	}

	/**
	 * updates how long the song ha been playing
	 * 
	 * @param currentPlayTime
	 */
	public void updateCurTime(String currentPlayTime) {
		curTime.setText(currentPlayTime);
	}

	/**
	 * updates the length of the song
	 * 
	 * @param fullTime
	 */
	public void updateTotalTime(String fullTime) {
		totalTime.setText(fullTime);
	}

	private void saveDB() throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
			out.writeObject(addedSongs);
		}
	}

	/**
	 * loads the songs
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<Track> load() throws ClassNotFoundException, IOException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
			FXMLController.addedSongs = (List<Track>) in.readObject();
		}
		return addedSongs;
	}

	/**
	 * exits the application and stops any playing songs
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void exit(WindowEvent event) throws IOException {
		stop();
		saveDB();
		System.exit(0);
	}

	// MediaPlayer Overrides
	@Override
	public void opened(Object arg0, Map<String, Object> arg1) {
		setGain(volumeLevel);

		volumeSlider.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				if (volumeSlider.isValueChanging()) {
					volumeLevel = volumeSlider.getValue() / 100.0;
					setGain(volumeLevel);
					mute = false;
					setMute(mute);
					muteBox.setSelected(mute);
				}
			}
		});
	}

	@Override
	public void progress(int arg0, long arg1, byte[] arg2, Map<String, Object> arg3) {
		songInPlaylist.refresh();
		final long temp = arg1;
		currentPlayTime = arg1 / 1000;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				manipulateSeekBar(totalPlayTime, currentPlayTime);
				milliToString(temp);
			}
		});
	}

	@Override
	public void statusUpdated(StreamPlayerEvent arg0) {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			songInPlaylist.setItems(songs);
			titleValue.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTitle()));
			artistValue.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getArtist()));
			genreValue.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getGenre()));
		} catch (NullPointerException e) {
		}
	}
}