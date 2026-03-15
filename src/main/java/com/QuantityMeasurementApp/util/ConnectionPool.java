package com.QuantityMeasurementApp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

	private static ConnectionPool instance;
	private final List<Connection> availableConnections = new ArrayList<>();
	private int activeConnections = 0;
	private final int maxPoolSize;

	private ConnectionPool() {
		this.maxPoolSize = ApplicationConfig.getPoolSize();
		initializePool();
	}

	private void initializePool() {
		try {
			Class.forName(ApplicationConfig.getDbDriver());
			for (int i = 0; i < maxPoolSize; i++) {
				Connection conn = DriverManager.getConnection(ApplicationConfig.getDbUrl(),
						ApplicationConfig.getDbUsername(), ApplicationConfig.getDbPassword());
				availableConnections.add(conn);
			}
			System.out.println("ConnectionPool: initialized with " + maxPoolSize + " connections");
		} catch (Exception e) {
			System.out.println("ConnectionPool: error initializing - " + e.getMessage());
		}
	}

	public static ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	public synchronized Connection getConnection() throws Exception {
		if (availableConnections.isEmpty()) {
			throw new Exception("ConnectionPool: no available connections");
		}
		Connection conn = availableConnections.remove(availableConnections.size() - 1);
		activeConnections++;
		return conn;
	}

	public synchronized void releaseConnection(Connection conn) {
		if (conn != null) {
			availableConnections.add(conn);
			activeConnections--;
		}
	}

	public String getPoolStatistics() {
		return "Active: " + activeConnections + ", Available: " + availableConnections.size();
	}
}