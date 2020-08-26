package com.laptrinhjavaweb.repository.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.jdbc.RentAreaRepository;
import com.laptrinhjavaweb.repository.jdbc.SimpleJpaRepository;

public class RentAreaRepositoryImpl extends SimpleJpaRepositoryImpl<RentAreaEntity> implements RentAreaRepository {

	@Override
	public Long save(RentAreaDTO rentAreaDTO) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = EntityManagerFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			// STEP 4: Execute a query
			String sql = "INSERT INTO rentarea (value,buildingid) VALUES (?,?)";
			stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, rentAreaDTO.getValue());
			stmt.setLong(2, rentAreaDTO.getBuildingId());
			stmt.executeUpdate();
			conn.commit();
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		} finally {

			try {
				if(rs!=null) {
					rs.close();
				}
				if (stmt != null)
					conn.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {

				se.printStackTrace();
			}
			return null;
		}

	}

}
