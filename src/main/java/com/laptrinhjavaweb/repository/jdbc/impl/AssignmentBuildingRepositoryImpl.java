package com.laptrinhjavaweb.repository.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.repository.jdbc.AssignmentBuildingRepository;

public class AssignmentBuildingRepositoryImpl extends SimpleJpaRepositoryImpl<AssignmentBuildingEntity>
		implements AssignmentBuildingRepository {

	@Override
	public void deleteByBuildingId(Long buildingId) {
		List<BuildingDTO> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = EntityManagerFactory.getInstance().getConnection();

			String sql = "DELETE assignmentbuilding WHERE buldingid = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, buildingId);

		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {

				se.printStackTrace();
			}
		}

	}

}
