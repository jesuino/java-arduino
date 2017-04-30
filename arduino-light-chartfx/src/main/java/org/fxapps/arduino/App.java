package org.fxapps.arduino;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

	XYChart.Series<Number, Number> series;
	LightSensorApp lightSensorApp;
	long initialTime;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		lightSensorApp = new LightSensorApp("/dev/ttyUSB0", false, this::updateChart);
		LineChart<Number, Number> chart = createChart();
		initialTime = System.currentTimeMillis();
		series = new XYChart.Series<>();
		ToggleButton tbLed = new ToggleButton("LED");
		
		tbLed.selectedProperty().addListener(c -> lightSensorApp.setTurnOnLed(tbLed.isSelected()));
		
		chart.getData().add(series);
		lightSensorApp.runArduinoProcess();
		VBox vb = new VBox(50, chart, tbLed);
		vb.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vb, 800, 600);
		stage.setScene(scene);
		stage.setTitle("Light control with monitoring");
		stage.show();
		stage.setOnCloseRequest(e -> System.exit(0));
	}

	private LineChart<Number, Number> createChart() {
		LineChart<Number, Number> chart = new LineChart<>(new NumberAxis(), new NumberAxis());
		chart.setTitle("Light Intensity");
		chart.getXAxis().setTickLabelsVisible(false);
		chart.getXAxis().setLabel("Time");
		chart.getXAxis().setTickMarkVisible(false);
		chart.setCreateSymbols(false);
		chart.setLegendVisible(false);
		chart.setAlternativeColumnFillVisible(false);
		return chart;
	}

	private void updateChart(Short s) {
		long time = System.currentTimeMillis() - initialTime;
		Data<Number, Number> data = new XYChart.Data<>(time, s);
		Platform.runLater(() -> series.getData().add(data));
	}

}
