package runner;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import client.InventoryReport;
import models.IOTThing;
import server.InventoryServer;

public class Runner {
	static ScheduledThreadPoolExecutor threadPoolExecutor;

	public static void main(String[] args) {
		threadPoolExecutor = new ScheduledThreadPoolExecutor(10);
        threadPoolExecutor.schedule(new InventoryServer(), 1, TimeUnit.SECONDS);
        threadPoolExecutor.schedule(new InventoryReport(new IOTThing()), 2, TimeUnit.SECONDS);

	}

}
