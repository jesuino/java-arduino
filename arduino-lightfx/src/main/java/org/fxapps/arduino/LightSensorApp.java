package org.fxapps.arduino;

import java.util.function.Consumer;
import org.sintef.jarduino.AnalogPin;
import org.sintef.jarduino.InvalidPinTypeException;
import org.sintef.jarduino.JArduino;

public class LightSensorApp extends JArduino {

	private Consumer<Short> receiveLight;

	public LightSensorApp(String port, Consumer<Short> receiveLight) {
		super(port);
		this.receiveLight = receiveLight;
	}

	@Override
	protected void setup() throws InvalidPinTypeException {
	}

	@Override
	protected void loop() throws InvalidPinTypeException {
		short light = analogRead(AnalogPin.A_0);
		receiveLight.accept(light);
		delay(100);
	}

}