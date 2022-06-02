package dbService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import db.MyDB;
import exceprions.MissingDataException;
import models.Device;
import models.IOTThing;

public class DBService {

	protected MyDB db;

	public DBService() {
		db = MyDB.getInstance();
	}

	public List<IOTThing> getAllIotThings() {
		return db.getAllIotThings();
	}

	public List<IOTThing> getAllIotThingsFiltered(String type, String model, String manufacturer) {
		List<IOTThing> result = db.getAllIotThings();
		if (type != null)
			result = result.stream().filter(iotThing -> iotThing.getType().toString().equalsIgnoreCase(type))
					.collect(Collectors.toList());
		if (model != null)
			result = result.stream().filter(iotThing -> iotThing.getModel().equalsIgnoreCase(model))
					.collect(Collectors.toList());
		if (manufacturer != null)
			result = result.stream().filter(iotThing -> iotThing.getManufacturer().equalsIgnoreCase(manufacturer))
					.collect(Collectors.toList());
		return result;
	}

	public synchronized IOTThing updateIotThingInDB(IOTThing iotThing) {
		if (db.createIotThing(iotThing) == null) {
			db.updateIotThing(iotThing);
		}
		printDB();
		return iotThing;
	}

	public boolean deleteIotThingByUUID(UUID uuid) {
		synchronized (db) {
			return db.deleteIotThingByUUID(uuid);
		}
	}

	public IOTThing getIotThingByUUID(UUID uuid) {
		IOTThing iotThing = db.getIotThingByUUID(uuid);
		if (iotThing == null)
			throw new MissingDataException("IotThing", uuid.toString());
		else
			return iotThing;
	}

	public Device getDeviceByUUID(UUID uuid) {
		Device device = db.getDeviceByUUID(uuid);
		if (device == null)
			throw new MissingDataException("Device", uuid.toString());
		else
			return device;
	}

	public List<Device> getAllDevices() {
		return db.getAllDevices();
	}

	public List<Device> getAllDevicesFiltered(String type, String model, String manufacturer) {
		List<Device> result = db.getAllDevices();
		if (type != null)
			result = result.stream().filter(device -> device.getType().toString().equalsIgnoreCase(type))
					.collect(Collectors.toList());
		if (model != null)
			result = result.stream().filter(device -> device.getModel().equalsIgnoreCase(model))
					.collect(Collectors.toList());
		if (manufacturer != null)
			result = result.stream().filter(device -> device.getManufacturer().equalsIgnoreCase(manufacturer))
					.collect(Collectors.toList());
		return result;
	}

	public void printDB() {
		System.out.println("\n==== Start printing DB ===");
		getAllIotThings().stream().forEach(thing -> {
			System.out.println("Thing:");
			System.out.println(thing);
			System.out.println("Devices:");
			System.out.println(thing.getDevices());
			System.out.println("-----------------");
		});
		System.out.println("==== End ===\n");
	}

}
