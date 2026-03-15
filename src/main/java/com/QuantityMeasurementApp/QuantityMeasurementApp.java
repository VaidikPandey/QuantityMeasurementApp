package com.QuantityMeasurementApp;

import com.QuantityMeasurementApp.controller.QuantityMeasurementController;
import com.QuantityMeasurementApp.model.QuantityDTO;
import com.QuantityMeasurementApp.model.QuantityMeasurementEntity;
import com.QuantityMeasurementApp.repository.IQuantityMeasurementRepository;
import com.QuantityMeasurementApp.repository.QuantityMeasurementCacheRepository;
import com.QuantityMeasurementApp.repository.QuantityMeasurementDatabaseRepository;
import com.QuantityMeasurementApp.service.QuantityMeasurementServiceImpl;
import com.QuantityMeasurementApp.util.ApplicationConfig;

import java.util.List;

public class QuantityMeasurementApp {

	private final IQuantityMeasurementRepository repo;
	private final QuantityMeasurementController controller;

	public QuantityMeasurementApp() {
		String repoType = ApplicationConfig.getRepositoryType();
		if (repoType.equalsIgnoreCase("database")) {
			this.repo = new QuantityMeasurementDatabaseRepository();
			System.out.println("App: using DATABASE repository");
		} else {
			this.repo = QuantityMeasurementCacheRepository.getInstance();
			System.out.println("App: using CACHE repository");
		}
		QuantityMeasurementServiceImpl service = new QuantityMeasurementServiceImpl(repo);
		this.controller = new QuantityMeasurementController(service);
	}

	public void deleteAllMeasurements() {
		repo.deleteAll();
	}

	public void closeResources() {
		repo.releaseResources();
		System.out.println("App: resources closed");
	}

	public static void main(String[] args) {

		QuantityMeasurementApp app = new QuantityMeasurementApp();

		// Compare 1 FEET and 12 INCH
		QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(12.0, "INCH", "LENGTH");
		app.controller.performCompare(q1, q2);

		// Convert 1 FEET to INCH
		app.controller.performConvert(q1, "INCH");

		// Add 1 FEET + 12 INCH
		app.controller.performAdd(q1, q2);

		// Subtract
		QuantityDTO q3 = new QuantityDTO(10.0, "FEET", "LENGTH");
		app.controller.performSubtract(q3, q2);

		// Divide
		QuantityDTO q4 = new QuantityDTO(2.0, "FEET", "LENGTH");
		app.controller.performDivide(q3, q4);

		// Show all saved measurements
		System.out.println("\n--- All Measurements in DB ---");
		List<QuantityMeasurementEntity> all = app.repo.findAll();
		for (QuantityMeasurementEntity e : all) {
			System.out.println(e);
		}

		System.out.println("Total count: " + app.repo.getTotalCount());
		System.out.println("Pool stats: " + app.repo.getPoolStatistics());

		// Cleanup
		app.deleteAllMeasurements();
		app.closeResources();
	}
}