package com.laptrinhjavaweb.repository.jdbc.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.jdbc.BuildingRepository;
import com.laptrinhjavaweb.repository.jdbc.JpaRepository;

public class BuildingRepositoryImpl extends SimpleJpaRepositoryImpl<BuildingEntity> implements BuildingRepository {

    @Override
    public List<BuildingEntity> getBuildings(BuildingSearchBuilder buildingSearchBuilder) {
        List<BuildingEntity> result = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try {

            conn = EntityManagerFactory.getInstance().getConnection();

            stmt = conn.createStatement();

            StringBuffer sql = new StringBuffer();
            sql.append("SELECT * FROM building b where 1=1");
            if(buildingSearchBuilder.getStaffId()!=null){
                sql.append(" join assignmentbuilding a on b.id = a.buildingid");
            }

            sql = buildSqlBuildingSearchComon(buildingSearchBuilder, sql);
            sql = buildingSqlBuildingSearchSpecial(sql, buildingSearchBuilder);
            if(buildingSearchBuilder.getStaffId()!=null){
                sql.append(" and a.staffid = "+buildingSearchBuilder.getStaffId()+ " ");
            }
            ResultSet rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                BuildingEntity buildingEntity = new BuildingEntity();
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                Integer numberofbasement = rs.getInt("numberofbasement");
                result.add(buildingEntity);
                buildingEntity.setName(name);
                buildingEntity.setNumberOfBasement(numberofbasement);
                buildingEntity.setId(id);
            }
            rs.close();
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

        return result;
    }

    private StringBuffer buildingSqlBuildingSearchSpecial(StringBuffer sql, BuildingSearchBuilder buildingSearchBuilder) {

        if (buildingSearchBuilder.getRentPriceFrom() != null) {
            sql.append("and" + buildingSearchBuilder.getRentPriceFrom() + "<=b.rentprice");
        } else if (buildingSearchBuilder.getRentPriceTo() != null) {
            sql.append("and" + buildingSearchBuilder.getRentPriceTo() + ">=b.rentprice");
        }
        if(buildingSearchBuilder.getRentPriceFrom() != null || buildingSearchBuilder.getRentPriceTo() != null){
            sql.append(" and EXISTS (SELECT * FROM rentarea (r WHERE  r.buildingid = b.id");
            if(buildingSearchBuilder.getRentPriceFrom() != null){
                sql.append("and r.value >= "+buildingSearchBuilder.getRentPriceFrom()+" ");
            }
            if(buildingSearchBuilder.getRentPriceTo() != null){
                sql.append("and r.value <= "+buildingSearchBuilder.getRentPriceTo()+" ");
            }
            sql.append("))");
        }

//        java 7
        /*if(buildingSearchBuilder.getTypes() != null){
            int lengthType = buildingSearchBuilder.getTypes().length;
            sql.append(" and (b.type like '%"+buildingSearchBuilder.getTypes()[0]+"%");
            for(int i=1;i<lengthType;i++){
                if(i>=1){
                    sql.append(" or b.type like '%"+buildingSearchBuilder.getTypes()[i]+"%");
                }
            }
            sql.append(")");
        }*/

        //Java 8
        if(buildingSearchBuilder.getTypes() != null && buildingSearchBuilder.getTypes().length>0){
            sql.append(" and (");
            String sqlType = Arrays.stream(buildingSearchBuilder.getTypes())
                            .map(item -> " b.type like '% "+ item + "%'")
                            .collect(Collectors.joining(" OR "));
            sql.append(sqlType);
            sql.append(" )");
        }


        return sql;
    }

    private StringBuffer buildSqlBuildingSearchComon(BuildingSearchBuilder buildingSearchBuilder, StringBuffer sql) {
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
                                    + field.get(buildingSearchBuilder));
                        }
                    }
                }
            }
            return sql;
        } catch (IllegalAccessException e) {
            return new StringBuffer();
        }

    }

//	@Override
//	public Long save(BuildingDTO buildingDTO) {
//		Connection conn = null;
//		PreparedStatement rs = null;
//		ResultSet result = null;
//		try {
//			conn = EntityManagerFactory.getInstance().getConnection();
//			conn.setAutoCommit(false);
//			StringBuffer sql = new StringBuffer();
//			sql.append("Insert INTO building ('name', 'street','district','numberOfBasement','floorArea') VALUES(?,?,?,?,?)");
//			
//			rs = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
//			rs.setString(1, buildingDTO.getName());
//			rs.setString(2, buildingDTO.getStreet());
//			rs.setString(3, buildingDTO.getDistrict());
//			rs.setLong(4, buildingDTO.getNumberOfBasement());
//			rs.setLong(5, buildingDTO.getFloorArea());
//			//setValue(buildingDTO, rs);
//			rs.executeUpdate();
//			conn.commit();
//			result = rs.getGeneratedKeys();
//			if (result.next()) {
//				return result.getLong(1);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			if(conn!=null) {
//				try {
//					conn.rollback();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		} finally {
//
//			try {
//				if (result != null) {
//					result.close();
//				}
//				if (rs != null)
//					rs.close();
//				if (conn != null)
//					conn.close();
//			} catch (SQLException se) {
//
//				se.printStackTrace();
//			}
//		} // end try
//		return null;
//
//	}


//	@Override
//	public BuildingDTO findById(Long buildingId) {
//		BuildingDTO buildingDTO = new BuildingDTO();
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = EntityManagerFactory.getInstance().getConnection();
//			String sql = "Select * FROM building WHERE id = ?";
//			stmt = conn.prepareStatement(sql);
//			stmt.setLong(1, buildingId);
//			rs = stmt.executeQuery();
//			while (rs.next()) {
//
//				Long id = rs.getLong("id");
//				String name = rs.getString("name");
//				String ward = rs.getString("ward");
//				String street = rs.getString("street");
//				String district = rs.getString("district");
//				String structure = rs.getString("structure");
//				buildingDTO.setId(id);
//				buildingDTO.setName(name);
//				buildingDTO.setWard(ward);
//				buildingDTO.setStreet(street);
//				buildingDTO.setDistrict(district);
//				buildingDTO.setStructure(structure);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (stmt != null)
//					stmt.close();
//				if (conn != null)
//					conn.close();
//			} catch (SQLException se) {
//
//				se.printStackTrace();
//			}
//			
//		}
//
//		return buildingDTO;
//	}

    @Override
    public Long saveWithTransaction(BuildingDTO buildingDTO) {
        Long buildingId = null;
        Connection conn = null;
        PreparedStatement rs = null;
        ResultSet result = null;
        try {
            conn = EntityManagerFactory.getInstance().getConnection();
            conn.setAutoCommit(false);
            StringBuffer sql = new StringBuffer();
            sql.append("Insert INTO building (name, street,district,numberOfBasement,floorArea) VALUES(?,?,?,?,?)");

            rs = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            rs.setString(1, buildingDTO.getName());
            rs.setString(2, buildingDTO.getStreet());
            rs.setString(3, buildingDTO.getDistrict());
            rs.setLong(4, buildingDTO.getNumberOfBasement());
            rs.setLong(5, buildingDTO.getFloorArea());
            rs.executeUpdate();
            conn.commit();
            result = rs.getGeneratedKeys();

            if (result.next()) {
                buildingId = result.getLong(1);
            }
            StringBuilder sqlSaveRentArea = new StringBuilder("INSERT INTO rentarea (value,buildingid) VALUES (?,?)");
            rs = conn.prepareStatement(sqlSaveRentArea.toString());

            for (String value : buildingDTO.getRentAreas()) {
                rs.setInt(1, Integer.parseInt(value));
                rs.setLong(2, buildingId);
                rs.executeUpdate();
                conn.commit();
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        } finally {

            try {
                if (result != null) {
                    result.close();
                }
                if (rs != null)
                    rs.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {

                se.printStackTrace();

            }

        } // end try
        return buildingId;


    }


}
