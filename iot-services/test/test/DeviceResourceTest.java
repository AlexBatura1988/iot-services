package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import beans.IotThingsFilterBean;
import dbService.DBService;
import dbService.MockDBService;
import jakarta.ws.rs.core.Response;
import models.Device;
import resources.DeviceResource;

class DeviceResourceTest {

	private DeviceResource resource;
	private DBService dbService;
	
	public DeviceResourceTest() {
		resource = new DeviceResource();
		dbService = new MockDBService();
	}

	@Test
	void testGetAllDevices() {
		IotThingsFilterBean bean = new IotThingsFilterBean();
		Response response = resource.getAllDevices(bean);
		assertEquals(dbService.getAllDevices(), response.getEntity());
		
		bean.setManufacturer("sony");
		response = resource.getAllDevices(bean);
		assertEquals(dbService.getAllDevicesFiltered(null, null, "sony"), response.getEntity());
	}

	@Test
	void testGetDeviceByID() {
		Device device = dbService.getAllDevices().get(0);
		Response response = resource.getDevice(device.getID().toString());
		assertEquals(device, response.getEntity());
	}

}
