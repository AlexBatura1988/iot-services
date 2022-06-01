package db;

import java.util.Map;
import java.util.UUID;

import models.Device;
import models.IOTThing;
import models.Type;

public class DBMock {
	private Map<UUID,IOTThing> iotThings;
	private Map<UUID,Device> devices;
	private static DBMock instance = null;
	
	private DBMock() {
				
	}

	public synchronized static DBMock getInstance() {
		if (instance == null) {
			instance = new DBMock();
		}

		return instance;
	}
	private void seedThings() {
		IOTThing thing = new IOTThing(Type.SENSOR,"a1","solar egde");	
	}

}
