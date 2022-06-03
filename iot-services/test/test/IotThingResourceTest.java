package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import beans.IotThingsFilterBean;
import dbService.DBService;
import dbService.MockDBService;
import jakarta.ws.rs.core.Response;
import models.IOTThing;
import resources.IotThingResource;

class IotThingResourceTest {

	IotThingResource iotThingResource;
	DBService dbService;

	public IotThingResourceTest() {
		iotThingResource = new IotThingResource();
		dbService = new MockDBService();
	}

	@Test
	void testGetAllIotThings() {
		IotThingsFilterBean bean = new IotThingsFilterBean();
		Response response = iotThingResource.getAllIotThings(bean);
		assertEquals(dbService.getAllIotThings(), response.getEntity());

		bean.setManufacturer("sony");
		response = iotThingResource.getAllIotThings(bean);
		assertEquals(dbService.getAllIotThingsFiltered(null, null, "sony"), response.getEntity());
	}

	@Test
	void testGetIotThing() {
		IOTThing iotThing = dbService.getAllIotThings().get(0);
		Response response = iotThingResource.getIotThing(iotThing.getID().toString());
		assertEquals(iotThing, response.getEntity());
	}

}
