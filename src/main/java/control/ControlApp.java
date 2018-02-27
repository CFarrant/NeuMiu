package control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;

public class ControlApp {

	@FXML
	private TreeView<?> playlistTree;
	
	@FXML
	private ListView<?> songInPlaylist;
	
	@FXML
	private Slider volumeSlider;
	
	@FXML
	private CheckBox muteButton;
	
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
	
}
