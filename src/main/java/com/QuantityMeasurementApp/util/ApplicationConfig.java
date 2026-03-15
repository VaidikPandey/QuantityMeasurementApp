package com.QuantityMeasurementApp.util;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

	private static final Properties properties = new Properties();

	static {
		try {
			InputStream input = ApplicationConfig.class.getClassLoader().getResourceAsStream("application.properties");
			if (input != null) {
				properties.load(input);
				System.out.println("ApplicationConfig: properties loaded successfully");
			} else {
				System.out.println("ApplicationConfig: properties file not found, using defaults");
			}
		} catch (Exception e) {
			System.out.println("ApplicationConfig: error loading properties - " + e.getMessage());
		}
	}

	public static String getDbUrl() {
		return properties.getProperty("db.url", "jdbc:h2:mem:quantitydb;DB_CLOSE_DELAY=-1");
	}

	public static String getDbUsername() {
		return properties.getProperty("db.username", "sa");
	}

	public static String getDbPassword() {
		return properties.getProperty("db.password", "");
	}

	public static String getDbDriver() {
		return properties.getProperty("db.driver", "org.h2.Driver");
	}

	public static int getPoolSize() {
		return Integer.parseInt(properties.getProperty("db.pool.size", "5"));
	}

	public static String getRepositoryType() {
		return properties.getProperty("repository.type", "cache");
	}
}