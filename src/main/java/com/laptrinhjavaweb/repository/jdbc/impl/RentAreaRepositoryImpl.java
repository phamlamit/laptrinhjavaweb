package com.laptrinhjavaweb.repository.jdbc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.repository.jdbc.RentAreaRepository;

public class RentAreaRepositoryImpl implements RentAreaRepository {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/javaweb62020module1";
	static final String USER = "root";
	static final String PASS = "12345";

	@Override
	public void save(RentAreaDTO rentAreaDTO) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			String sql = "INSERT INTO rentarea (value,buildingid) VALUES (?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, rentAreaDTO.getValue());
			stmt.setLong(2, rentAreaDTO.getBuildingId());
			int rs = stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// STEP 3: Open a connection
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					conn.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {

				se.printStackTrace();
			}
		}

	}

}
