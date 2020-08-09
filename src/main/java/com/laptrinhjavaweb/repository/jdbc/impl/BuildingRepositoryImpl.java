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
			// STEP 2: Register JDBC driver

			Class.forName(JDBC_DRIVER);
			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			stmt = conn.createStatement();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM building b where 1=1");
//			if(buildingSearchBuilder.getName() != null) {
//				sql.append(" b.name like '%"+buildingSearchBuilder.getName()+"%'" );
//			}
			sql = buildSqlBuildingSearch(buildingSearchBuilder, sql);
			ResultSet rs = stmt.executeQuery(sql.toString());
			// STEP 5: Extract data from result set
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
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

		return result;
	}

	private StringBuffer buildSqlBuildingSearch(BuildingSearchBuilder buildingSearchBuilder, StringBuffer sql) {
//		if(buildingSearchBuilder.getName() != null) {
//			sql.append(" b.name like '%"+buildingSearchBuilder.getName()+"%'" );
//		}
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
	public Boolean createBuilding(BuildingDTO buildingDTO) {
		int result = 0;
		Connection conn = null;
		PreparedStatement rs = null;
		try {
			Class.forName(JDBC_DRIVER);
			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
//			String sql = "Insert INTO building "
//					+ "(name,street,district,structure,numberOfBasement,floorArea,direction,"
//					+ "level,rentPrice,rentPriceDescription,serviceFee,carFee,motoFee,overtimeFee,waterFee,"
//					+ "electricityFee,deposit,payment,rentTime,decorationTime,brokerageFee,types,note,"
//					+ "linkOfBuilding) " + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			StringBuffer sql = new StringBuffer();
			sql.append("Insert INTO building (");
			sql = buildSqlCreateBuildingDTO(buildingDTO, sql);
			rs = conn.prepareStatement(sql.toString());
			setValue(buildingDTO, rs);

			// rs = conn.prepareStatement(sql);
//			rs.setString(1, buildingDTO.getName());
//			rs.setString(2, buildingDTO.getStreet());
//			rs.setString(3, buildingDTO.getDistrict());
//			rs.setString(4, buildingDTO.getStructure());
//			rs.setInt(5, buildingDTO.getNumberOfBasement());
//			rs.setInt(6, buildingDTO.getFloorArea());
//			rs.setString(7, buildingDTO.getDirection());
//			rs.setString(8, buildingDTO.getLevel());
//			rs.setLong(9, buildingDTO.getRentPrice());
//			rs.setString(10, buildingDTO.getRentPriceDescription());
//			rs.setString(11, buildingDTO.getServiceFee());
//			rs.setString(12, buildingDTO.getCarFee());
//			rs.setString(13, buildingDTO.getMotoFee());
//			rs.setString(14, buildingDTO.getOvertimeFee());
//			rs.setString(15, buildingDTO.getWaterFee());
//			rs.setString(16, buildingDTO.getElectricityFee());
//			rs.setString(17, buildingDTO.getDeposit());
//			rs.setString(18, buildingDTO.getPayment());
//			rs.setString(19, buildingDTO.getRentTime());
//			rs.setDouble(20, (Double) buildingDTO.getDecorationTime());
//			rs.setDouble(21, (Double) buildingDTO.getBrokerageFee());
//			String types = "";
//			String[] type = buildingDTO.getTypes();
//			for (int i = 0; i < type.length; i++) {
//				if (i == type.length - 1) {
//					types += type[i];
//				} else {
//					types += type[i] + ",";
//				}
//			}
//			rs.setString(22, types);
//			rs.setString(23, buildingDTO.getNote());
//			rs.setString(24, buildingDTO.getLinkOfBuilding());
			result = rs.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (rs != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

		return result == 1;
	}

	@Override
	public Boolean createRentArea(BuildingDTO buildingDTO) {

		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			String sql = "INSERT INTO rentarea (value,buildingid) VALUES (?,(SELECT id FROM building  WHERE 1 =1 ORDER BY id desc limit 1))";
			String rentArea = buildingDTO.getRentArea();
			String[] valueRentArea = rentArea.split("\\,");

			for (String value : valueRentArea) {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, Integer.parseInt(value));
				result = stmt.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// STEP 3: Open a connection
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

		return result == 1;
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
						sql.append(field.getName()+",");
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

}
