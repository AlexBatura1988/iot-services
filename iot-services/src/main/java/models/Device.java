package models;

import java.util.Random;

public class Device extends Hardware {
	private double reading;
	
	public Device() {
		
	}

	public Device(Type type, String model, String manufacturer) {
		super(type, model, manufacturer);
		setReading(reading);
	}

	public double getReading() {
		return reading;
	}

	public void setReading(double reading) {
		this.reading = reading;
	}
	
	public void simulateReading() {
		Random random = new Random();
		setReading(random.nextDouble());
	}	

}
