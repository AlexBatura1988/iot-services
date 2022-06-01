package models;

import java.util.ArrayList;
import java.util.List;

public class IOTThing extends Hardware {
	
	private List<Device>devices;
	
	public IOTThing() {
		
		
	};
	
	public IOTThing(Type type, String model, String manufacturer) {
		super(type, model, manufacturer);
		devices = new ArrayList<Device>();
	}
	
	
	

}
