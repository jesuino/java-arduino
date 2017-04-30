package org.fxapps.arduino;

public class App {
	
	public static void main(String[] args) {
		// remember to use the correct port in your project
		new LightSensorApp("/dev/ttyUSB0" , System.out::println)
				.runArduinoProcess();
	}

}
