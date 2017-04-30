package org.fxapps.arduino;

import java.util.function.Consumer;
import org.sintef.jarduino.AnalogPin;
import org.sintef.jarduino.DigitalPin;
import org.sintef.jarduino.InvalidPinTypeException;
import org.sintef.jarduino.JArduino;

public class LightSensorApp extends JArduino {

	private final DigitalPin LED_PIN = DigitalPin.PIN_2;
	private Consumer<Short> receiveLight;
	private boolean turnOnLed;

	public LightSensorApp(String port, boolean led, Consumer<Short> receiveLight) {
		super(port);
		this.turnOnLed = led;
		this.receiveLight = receiveLight;
	}

	@Override
	protected void setup() throws InvalidPinTypeException {
		pinMode(LED_PIN, OUTPUT);
	}

	@Override
	protected void loop() throws InvalidPinTypeException {
		short light = analogRead(AnalogPin.A_0);
		digitalWrite(LED_PIN, turnOnLed? HIGH : LOW);
		System.out.println(light);
		receiveLight.accept((short) map(light, 300, 1023, 0, 100));
		delay(100);
	}

	public void setTurnOnLed(boolean turnOnLed) {
		this.turnOnLed = turnOnLed;
	}


}