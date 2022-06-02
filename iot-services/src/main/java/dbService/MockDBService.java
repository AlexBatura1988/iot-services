package dbService;

public class MockDBService extends DBService {

	public MockDBService() {
		super();
		db.seedData();
	}

}
