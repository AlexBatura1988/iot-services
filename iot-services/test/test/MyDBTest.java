package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import db.MyDB;
import models.Device;
import models.IOTThing;

class MyDBTest {

	MyDB myDB;

	public MyDBTest() {
		myDB = MyDB.getInstance();
	}

	@Test
	void testGetAllIotThings() {
		IOTThing iotThing1 = new IOTThing();
		IOTThing iotThing2 = new IOTThing();
		iotThing1.simulateInventoryChange();
		iotThing2.simulateInventoryChange();
		
		myDB.createIotThing(iotThing1);
		myDB.createIotThing(iotThing2);
		
		List<IOTThing> list1 = Arrays.asList(iotThing1, iotThing2).stream()
				.sorted((o1, o2) -> o1.hashCode() - o2.hashCode()).collect(Collectors.toList());
		List<IOTThing> list2 = myDB.getAllIotThings().stream()
				.sorted((o1, o2) -> o1.hashCode() - o2.hashCode()).collect(Collectors.toList());
		
		assertEquals(list1, list2);
	}
	
	@Test
	void testGetInstance() {
		MyDB myDB1 = MyDB.getInstance();
		MyDB myDB2 = MyDB.getInstance();
		assertSame(myDB1, myDB2);
	}
	@Test
	void testCreateIotThing() {
		IOTThing iotThing1 = new IOTThing();
		IOTThing iotThing2 = myDB.createIotThing(iotThing1);
		assertEquals(iotThing1, iotThing2);
		iotThing2 = myDB.createIotThing(iotThing1);
		assertNull(iotThing2);
	}

	@Test
	void testUpdateIotThing() {
		IOTThing iotThing1 = new IOTThing();
		assertEquals(null, myDB.updateIotThing(iotThing1));
		
		myDB.createIotThing(iotThing1);
		
		iotThing1.setModel("test");
		iotThing1.setManufacturer("sony");
		
		assertEquals(iotThing1, myDB.updateIotThing(iotThing1));
	}


	@Test
	void testGetIotThingByUUID() {
		IOTThing iotThing1 = new IOTThing();
		myDB.createIotThing(iotThing1);
		assertEquals(iotThing1, myDB.getIotThingByUUID(iotThing1.getID()));
	}

}
