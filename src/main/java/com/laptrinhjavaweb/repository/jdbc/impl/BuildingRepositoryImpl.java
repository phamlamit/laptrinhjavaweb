package com.laptrinhjavaweb.repository.jdbc.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.repository.jdbc.BuildingRepository;

public class BuildingRepositoryImpl implements BuildingRepository {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/javaweb62020module1";
	static final String USER = "root";
	static final String PASS = "12345";

	@Override
	public List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder) {
		List<BuildingDTO> result = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM building b where 1=1");

			sql = buildSqlBuildingSearch(buildingSearchBuilder, sql);
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				BuildingDTO buildingDTO = new BuildingDTO();
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				Integer numberofbasement = rs.getInt("numberofbasement");
				result.add(buildingDTO);
				buildingDTO.setName(name);
				buildingDTO.setNumberOfBasement(numberofbasement);
				buildingDTO.setId(id);
			}
			rs.close();
		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

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

		return result;
	}

	private StringBuffer buildSqlBuildingSearch(BuildingSearchBuilder buildingSearchBuilder, StringBuffer sql) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				if (!field.getName().equals("nameEmployeeInCharge") && !field.getName().startsWith("rentArea")
						&& !field.getName().startsWith("rentPrice") && !field.getName().equals("types")) {
					String fieldType = field.getType().getName();
					field.setAccessible(true);
					if (fieldType.equals("java.lang.String")) {
						if (field.get(buildingSearchBuilder) != null) {
							sql.append(" and b." + field.getName().toLowerCase() + " like '%"
									+ field.get(buildingSearchBuilder).toString() + "%'");
						}
					} else if (fieldType.equals("java.lang.Integer")) {
						if (field.get(buildingSearchBuilder) != null) {
							sql.append(" and b." + field.getName().toLowerCase() + " = "
									+ field.get(buildingSearchBuilder) + "");
						}
					} else if (fieldType.equals("java.lang.Double")) {
						if (field.get(buildingSearchBuilder) != null) {
							sql.append(" and b." + field.getName().toLowerCase() + " = "
									+ field.get(buildingSearchBuilder) + "");
						}
					}
				}
			}
			return sql;
		} catch (IllegalAccessException e) {
			return new StringBuffer();
		}

	}

	@Override
	public Long save(BuildingDTO buildingDTO) {
		Connection conn = null;
		PreparedStatement rs = null;
		ResultSet result = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			StringBuffer sql = new StringBuffer();
			sql.append("Insert INTO building (");
			sql = buildSqlCreateBuildingDTO(buildingDTO, sql);
			rs = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			setValue(buildingDTO, rs);
			rs.executeUpdate();
			result = rs.getGeneratedKeys();
			if (result.next()) {
				return result.getLong(1);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (result != null) {
					result.close();
				}
				if (rs != null)
					conn.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {

				se.printStackTrace();
			}
		} // end try
		return null;

	}

	private void setValue(BuildingDTO buildingDTO, PreparedStatement rs) {
		int n = 1;
		Field[] fields = BuildingDTO.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				if (!field.getName().equals("id") && !field.getName().equals("types")
						&& !field.getName().equals("rentArea")) {
					String fieldType = field.getType().getName();
					field.setAccessible(true);
					if (fieldType.equals("java.lang.String")) {
						if (field.get(buildingDTO) != null) {
							rs.setString(n, field.get(buildingDTO).toString());
							n++;
						}
					} else if (fieldType.equals("java.lang.Integer")) {
						if (field.get(buildingDTO) != null) {
							rs.setInt(n, (int) field.get(buildingDTO));
							n++;
						}
					} else if (fieldType.equals("java.lang.Double")) {
						if (field.get(buildingDTO) != null) {
							rs.setDouble(n, (double) field.get(buildingDTO));
							n++;
						}

					} else if (fieldType.equals("java.lang.Long")) {
						if (field.get(buildingDTO) != null) {
							rs.setLong(n, (long) field.get(buildingDTO));
							n++;
						}

					}
				}
			}
			String[] types = buildingDTO.getTypes();
			String typesValue = "";
			for (int i = 0; i < types.length; i++) {
				if (i == types.length - 1) {
					typesValue += types[i];
				} else {
					typesValue += types[i] + ",";
				}
			}
			rs.setString(n, typesValue);

		} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private StringBuffer buildSqlCreateBuildingDTO(BuildingDTO buildingDTO, StringBuffer sql) {
		int n = 0;
		Field[] fields = BuildingDTO.class.getDeclaredFields();
		for (Field field : fields) {
			if (!field.getName().equals("id") && !field.getName().equals("types")
					&& !field.getName().equals("rentArea")) {
				field.setAccessible(true);
				try {
					if (field.get(buildingDTO) != null) {
						sql.append(field.getName() + ",");
						n++;
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		sql.append("types) values(");
		for (int i = 0; i < n; i++) {
			sql.append("?,");
		}
		sql.append("?)");
		return sql;

	}

	@Override
	public BuildingDTO findById(Long buildingId) {
		BuildingDTO buildingDTO = new BuildingDTO();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "Select * FROM building WHERE id = ?";
			stmt=conn.prepareStatement(sql);
			stmt.setLong(1, buildingId);
			rs=stmt.executeQuery();
			while(rs.next()) {
				
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String ward = rs.getString("ward");
				String street = rs.getString("street");
				String district = rs.getString("district");
				String structure = rs.getString("structure");
				buildingDTO.setId(id);
				buildingDTO.setName(name);
				buildingDTO.setWard(ward);
				buildingDTO.setStreet(street);
				buildingDTO.setDistrict(district);
				buildingDTO.setStructure(structure);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return buildingDTO;
	}

}
