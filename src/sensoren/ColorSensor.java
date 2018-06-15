package sensoren;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.ColorDetector;
import lejos.robotics.ColorIdentifier;

/**
 * @author mitchellsitohang gekopieerd van exercises om een kleur te kunnen
 *         volgen
 */

public class ColorSensor implements ColorDetector, ColorIdentifier {
	EV3ColorSensor sensor;
	float[] sample;

	
	// ColorSensor = maak ColorSensor aan
	
	public ColorSensor(Port port) {
		sensor = new EV3ColorSensor(port);
		setAmbientMode();
		setFloodLight(false);
	}

	
	// EV3ColorSensor = return onderliggende EV3ColorSensor object
	
	public EV3ColorSensor getSensor() {
		return sensor;
	}

	
	// setRedMode: set color sensor naar ambient level mode
	
	public void setAmbientMode() {
		sensor.setCurrentMode("Ambient");
		sample = new float[sensor.sampleSize()];
	}

	
	// setRedMode: set color sensor naar rood licht mode
	
	public void setRedMode() {
		sensor.setCurrentMode("Red");
		sample = new float[sensor.sampleSize()];
	}

	
	// setColorIDMode: set color sensor naar color ID mode
	
	public void setColorIdMode() {
		sensor.setCurrentMode("ColorID");
		sample = new float[sensor.sampleSize()];
	}

	
	// setRGBMode: set color sensor naar RGB mode
	
	public void setRGBMode() {
		sensor.setCurrentMode("RGB");
		sample = new float[sensor.sampleSize()];
	}

	
	// getColorID: geeft gedetecteerde kleur terug, gebruik met color ID mode

	@Override
	public int getColorID() {
		sensor.fetchSample(sample, 0);
		return (int) sample[0];
	}


	
	// getColor: bij RGB/white, zorgt ervoor dat Color object teruggegeven (relatief) wordt
	// dit is niet de echte RGB waarde, maar de intensiteit van de gedetecteerde kleur
	
	@Override
	public Color getColor() {
		sensor.fetchSample(sample, 0);
		return new Color((int) (sample[0] * 255), (int) (sample[1] * 255), (int) (sample[2] * 255));
	}

	
	// getAmbient: geef sterkte van ambient terug, sensor led moet uitstaan (0-1)
	
	public float getAmbient() {
		sensor.fetchSample(sample, 0);
		return sample[0];
	}

	
	// getRed: geef rood licht terug, sensor led moet hierbij rood zijn (0-1)
	
	public float getRed() {
		sensor.fetchSample(sample, 0);
		return sample[0];
	}

	// close: wanneer taak volbracht is
	
	public void close() {
		sensor.close();
	}

	
	// isFloodLightOn: true als hij aanstaat, false als hij uitstaat
	
	public boolean isFloodLightOn() {
		return sensor.isFloodlightOn();
	}


	
	// setFloodLight boolean: zet de floodlight aan (true) of uit (false)
	
	public void setFloodLight(boolean on) {
		sensor.setFloodlight(on);
	}

	
	// setFloodLight: zorgt ervoor dat de kleur bepaald wordt voor floodlight

	public void setFloodLight(int color) {
		sensor.setFloodlight(color);
	}


	// colorName: zet de integer van kleur om naar de naam van de kleur
	
	public static String colorName(int color) {
		switch (color) {
		case Color.NONE:
			return "None";

		case Color.BLACK:
			return "Black";

		case Color.BLUE:
			return "Blue";

		case Color.BROWN:
			return "Brown";

		case Color.CYAN:
			return "Cyan";

		case Color.DARK_GRAY:
			return "Dark Gray";

		case Color.GRAY:
			return "Gray";

		case Color.GREEN:
			return "Green";

		case Color.LIGHT_GRAY:
			return "Light Gray";

		case Color.MAGENTA:
			return "Magenta";

		case Color.ORANGE:
			return "Orange";

		case Color.PINK:
			return "Pink";

		case Color.RED:
			return "Red";

		case Color.WHITE:
			return "White";

		case Color.YELLOW:
			return "Yellow";
		}

		return "";
	}
}