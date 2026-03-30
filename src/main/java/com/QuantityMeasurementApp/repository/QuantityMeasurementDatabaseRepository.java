package com.QuantityMeasurementApp.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.QuantityMeasurementApp.exception.DatabaseException;
import com.QuantityMeasurementApp.model.QuantityMeasurementEntity;
import com.QuantityMeasurementApp.util.ConnectionPool;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

	private final ConnectionPool connectionPool;

	public QuantityMeasurementDatabaseRepository() {
		this.connectionPool = ConnectionPool.getInstance();
		initializeSchema();
		System.out.println("DatabaseRepository: initialized successfully");
	}

	private void initializeSchema() {
		String sql = "CREATE TABLE IF NOT EXISTS quantity_measurement_entity ("
				+ "id BIGINT AUTO_INCREMENT PRIMARY KEY, " + "operation VARCHAR(50) NOT NULL, "
				+ "measurement_type VARCHAR(50) NOT NULL, " + "result VARCHAR(255) NOT NULL, "
				+ "is_error BOOLEAN DEFAULT FALSE, " + "message VARCHAR(255), "
				+ "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
		Connection conn = null;
		try {
			conn = connectionPool.getConnection();
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			System.out.println("DatabaseRepository: schema initialized");
		} catch (Exception e) {
			throw new DatabaseException("Failed to initialize schema", e);
		} finally {
			connectionPool.releaseConnection(conn);
		}
	}

	@Override
	public void save(QuantityMeasurementEntity entity) {
		String sql = "INSERT INTO quantity_measurement_entity "
				+ "(operation, measurement_type, result, is_error, message) " + "VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;
		try {
			conn = connectionPool.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, entity.getOperation());
			ps.setString(2, entity.getMeasurementType());
			ps.setString(3, entity.getResult() != null ? entity.getResult() : "");
			ps.setBoolean(4, entity.hasError());
			ps.setString(5, entity.getMessage());
			ps.executeUpdate();
			System.out.println("DatabaseRepository: saved - " + entity);
		} catch (Exception e) {
			throw new DatabaseException("Failed to save entity", e);
		} finally {
			connectionPool.releaseConnection(conn);
		}
	}

	@Override
	public List<QuantityMeasurementEntity> findAll() {
		String sql = "SELECT * FROM quantity_measurement_entity";
		List<QuantityMeasurementEntity> list = new ArrayList<>();
		Connection conn = null;
		try {
			conn = connectionPool.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(mapRow(rs));
			}
		} catch (Exception e) {
			throw new DatabaseException("Failed to retrieve all entities", e);
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return list;
	}

	@Override
	public List<QuantityMeasurementEntity> findByOperation(String operation) {
		String sql = "SELECT * FROM quantity_measurement_entity WHERE operation = ?";
		List<QuantityMeasurementEntity> list = new ArrayList<>();
		Connection conn = null;
		try {
			conn = connectionPool.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, operation);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(mapRow(rs));
			}
		} catch (Exception e) {
			throw new DatabaseException("Failed to retrieve by operation", e);
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return list;
	}

	@Override
	public List<QuantityMeasurementEntity> findByMeasurementType(String measurementType) {
		String sql = "SELECT * FROM quantity_measurement_entity WHERE measurement_type = ?";
		List<QuantityMeasurementEntity> list = new ArrayList<>();
		Connection conn = null;
		try {
			conn = connectionPool.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, measurementType);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(mapRow(rs));
			}
		} catch (Exception e) {
			throw new DatabaseException("Failed to retrieve by measurement type", e);
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return list;
	}

	@Override
	public int getTotalCount() {
		String sql = "SELECT COUNT(*) FROM quantity_measurement_entity";
		Connection conn = null;
		try {
			conn = connectionPool.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw new DatabaseException("Failed to get total count", e);
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return 0;
	}

	@Override
	public void deleteAll() {
		String sql = "DELETE FROM quantity_measurement_entity";
		Connection conn = null;
		try {
			conn = connectionPool.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("DatabaseRepository: all records deleted");
		} catch (Exception e) {
			throw new DatabaseException("Failed to delete all entities", e);
		} finally {
			connectionPool.releaseConnection(conn);
		}
	}

	@Override
	public String getPoolStatistics() {
		return connectionPool.getPoolStatistics();
	}

	@Override
	public void releaseResources() {
		System.out.println("DatabaseRepository: resources released");
	}

	private QuantityMeasurementEntity mapRow(ResultSet rs) throws SQLException {
		String operation = rs.getString("operation");
		String measurementType = rs.getString("measurement_type");
		String result = rs.getString("result");
		boolean isError = rs.getBoolean("is_error");
		String message = rs.getString("message");
		if (isError) {
			return new QuantityMeasurementEntity(operation, measurementType, message, true);
		}
		return new QuantityMeasurementEntity(operation, measurementType, result);
	}
}