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
import com.laptrinhjavaweb.entity.StaffEntity;
import com.laptrinhjavaweb.repository.jdbc.AssignmentBuildingRepository;

public class AssignmentBuildingRepositoryImpl extends SimpleJpaRepositoryImpl<AssignmentBuildingEntity>
		implements AssignmentBuildingRepository {

	@Override
	public void delete(Long buildingId, Long staffId) {
		List<BuildingDTO> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = EntityManagerFactory.getInstance().getConnection();

			String sql = "DELETE FROM assignmentbuilding WHERE buildingid = ? and staffid = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, buildingId);
			stmt.setLong(2,staffId);
			stmt.executeUpdate();

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

	@Override
	public boolean checked(Long buildingId, long staffId) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			conn = EntityManagerFactory.getInstance().getConnection();

			String sql = "SELECT * FROM assignmentbuilding WHERE buildingid = ? and staffid = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, buildingId);
			stmt.setLong(2,staffId);
			rs = stmt.executeQuery();
			if(rs.next()){
				return true;
			}
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
		return false;
	}

	@Override
	public List<Long> getStaff(Long buildingId) {
		String sql = "SELECT * FROM assignmentbuilding a, user s, user_role r WHERE a.staffid = s.id and s.id = r.userid and a.buildingid = " + buildingId;
		List<AssignmentBuildingEntity> listAssignmentBuildingEntity = fillAll(sql);
		List<Long> result =new ArrayList<>();
		for(AssignmentBuildingEntity assignmentBuildingEntity : listAssignmentBuildingEntity){
			result.add(assignmentBuildingEntity.getStaffid());
		}

		return result;
	}

}
