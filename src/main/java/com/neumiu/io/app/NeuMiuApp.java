package com.neumiu.io.app;

import com.neumiu.io.control.FXMLController;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The NeuMiu Application Class
 * @author NeuMiu Team
 */
public class NeuMiuApp extends Application {

	private FXMLController control = new FXMLController();
	
	/**
	 * Launches the Application
	 * @param args (String[])
	 */
	public static void main(String[] args) {
		Application.launch(NeuMiuApp.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/NMApp.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("NeuMiu");
		stage.show();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				try {
					control.exit(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
