package com.neumiu.io.control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.neumiu.io.utils.ApplicationData;

public class ApplicationController {

	private FXMLController fxml;
	private PlaylistController playlist;
	private TrackController track;
	private ApplicationData appData;
	private String fileName = "NeuMiu.db";
	
	public ApplicationController() {
		fxml = new FXMLController();
		playlist = new PlaylistController();
		track = new TrackController();
		try {
			this.loadApplicationData();
		} catch (ClassNotFoundException | IOException e) {
			appData = new ApplicationData();
		}
	}

	private void saveApplicationData() throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
			out.writeObject(this.appData);
		}
	}
	
	public void loadApplicationData() throws ClassNotFoundException, IOException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
			this.appData = (ApplicationData)in.readObject();
		}
	}
}
