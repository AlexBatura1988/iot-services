package db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import models.Device;
import models.IOTThing;
import models.Manufacturer;
import models.Type;

public class MyDB {

	private static MyDB instance = null;
	private Map<UUID, IOTThing> iotThings;
	private Map<UUID, Device> devices;
	private boolean dbMocked = false;

	public static synchronized MyDB getInstance() {
		if (instance == null)
			instance = new MyDB();
		return instance;
	}

	private MyDB() {
		iotThings = new HashMap<UUID, IOTThing>();
		devices = new HashMap<UUID, Device>();
	}

	public IOTThing createIotThing(IOTThing iotThing) {
		if (!iotThings.containsKey(iotThing.getID())) {
			iotThings.put(iotThing.getID(), iotThing);
			iotThing.getDevices().forEach(device -> {
				devices.put(device.getID(), device);
			});
			return iotThings.get(iotThing.getID());
		} else {
			return null;
		}
	}

	public IOTThing updateIotThing(IOTThing iotThing) {
		if (iotThings.containsKey(iotThing.getID())) {

			// clear things devices before update
			iotThings.get(iotThing.getID()).getDevices().forEach(device -> {
				devices.remove(device.getID());
			});
			// update
			iotThings.put(iotThing.getID(), iotThing);
			iotThing.getDevices().forEach(device -> {
				devices.put(device.getID(), device);
			});

			return iotThings.get(iotThing.getID());
		} else {
			return null;
		}
	}

	public List<IOTThing> getAllIotThings() {
		return iotThings.values().stream().collect(Collectors.toList());
	}

	public IOTThing getIotThingByUUID(UUID uuid) {
		return iotThings.get(uuid);
	}

	public boolean deleteIotThingByUUID(UUID uuid) {
		if (!iotThings.containsKey(uuid))
			return false;
		else {
			iotThings.get(uuid).getDevices().forEach(device -> {
				devices.remove(device.getID());
			});
			iotThings.remove(uuid);
			return true;
		}
	}

	public void seedData() {
		if (!dbMocked) {
			dbMocked = true;
			IOTThing iotThing1 = new IOTThing(Type.CONTROLLER, "Model A", Manufacturer.APPLE.toString());
			iotThing1.simulateInventoryChange();
			IOTThing iotThing2 = new IOTThing(Type.CONTROLLER, "Model B", Manufacturer.SONY.toString());
			iotThing2.simulateInventoryChange();
			IOTThing iotThing3 = new IOTThing(Type.CONTROLLER, "Model C", Manufacturer.SAMSUNG.toString());
			iotThing3.simulateInventoryChange();
			IOTThing iotThing4 = new IOTThing(Type.CONTROLLER, "Model D", Manufacturer.HONEYWELL.toString());
			iotThing4.simulateInventoryChange();
			IOTThing iotThing5 = new IOTThing(Type.CONTROLLER, "Model E", Manufacturer.HONEYWELL.toString());
			iotThing5.simulateInventoryChange();
			createIotThing(iotThing1);
			createIotThing(iotThing2);
			createIotThing(iotThing3);
			createIotThing(iotThing4);
			createIotThing(iotThing5);
		}
	}

	public List<Device> getAllDevices() {
		return devices.values().stream().collect(Collectors.toList());
	}

	public Device getDeviceByUUID(UUID uuid) {
		return devices.get(uuid);
	}

}
