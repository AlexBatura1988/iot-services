package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IOTThing extends Hardware {
	private final int MAX_DEVICE_NUM = 10;

	private List<Device> devices;

	public IOTThing() {

	};

	public IOTThing(Type type, String model, String manufacturer) {
		super(type, model, manufacturer);
		devices = new ArrayList<Device>();
	}

	public IOTThing(String model, List<Device> devices) {
		super(Type.CONTROLLER, model, Manufacturer.values()[new Random().nextInt(5)].toString());
		this.devices = devices;
	}

	public void simulateInventoryChange() {
		devices.clear();
		for (int i = 0; i < Math.random() * MAX_DEVICE_NUM; i++) {
			devices.add(createRandomDevice());
		}

	}

	private Device createRandomDevice() {
		return new Device(Type.values()[new Random().nextInt(3)], "model-m" + new Random().nextInt(100),
				Manufacturer.values()[new Random().nextInt(5)].toString());
	}

	public List<Device> getDevices() {
		return devices;
	}

}
