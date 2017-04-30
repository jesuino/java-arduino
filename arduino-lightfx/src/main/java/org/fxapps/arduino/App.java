package org.fxapps.arduino;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
	
	LightSensorApp lightSensorApp;

	public static void main(String[] args) {   // this method should not be required on fx apps
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		Label lbl = new Label();
		Scene scene = new Scene(new StackPane(lbl),300, 100);
		lightSensorApp = new LightSensorApp("/dev/ttyUSB0" , s -> 
			Platform.runLater(() -> lbl.setText("light: " + s ))
		);
		lightSensorApp.runArduinoProcess();
		stage.setScene(scene);
		stage.setTitle("Light control with monitoring");
		stage.show();
	}

}
