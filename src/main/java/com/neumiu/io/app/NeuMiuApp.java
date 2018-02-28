package com.neumiu.io.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NeuMiuApp extends Application{

	public static void main(String[] args) {
		Application.launch(NeuMiuApp.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/NMApp.fxml"));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("NeuMiu");
		stage.show();
	}

}
