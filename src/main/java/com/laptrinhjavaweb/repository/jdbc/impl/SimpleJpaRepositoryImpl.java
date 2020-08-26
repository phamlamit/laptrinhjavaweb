package com.laptrinhjavaweb.repository.jdbc.impl;

import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.repository.jdbc.SimpleJpaRepository;

public class SimpleJpaRepositoryImpl<T> implements SimpleJpaRepository<T> {

	@Override
	public Long save(Object object) {
		Long buildingId = null;
		Connection conn = null;
		PreparedStatement rs = null;
		ResultSet result = null;
		try {
			conn = EntityManagerFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			String sql = buildSqlInsert();
			
			rs = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int index=1;
			for(Field feild: object.getClass().getDeclaredFields()) {
				feild.setAccessible(true);
				rs.setObject(index, feild.get(object));
				index++;
			}
			rs.executeUpdate();
			conn.commit();
			result = rs.getGeneratedKeys();
			
			if (result.next()) {
				buildingId=  result.getLong(1);
			}
			
		} catch (SQLException|IllegalAccessException e) {
			// TODO Auto-generated catch block
			
			if(conn!=null) {
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

	private String buildSqlInsert() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType p = (ParameterizedType) t;
		Class<T> zClass = (Class<T>) p.getActualTypeArguments()[0];
		String tableName = "";
		if (zClass.isAnnotationPresent(Target.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuilder fields = new StringBuilder("");
		StringBuilder params = new StringBuilder("");
		for (Field field : zClass.getDeclaredFields()) {
			if (fields.length() > 1) {
				fields.append(",");
				params.append(",");
			}
			if(field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name());
				params.append("?");
			}

		}
		String sql = "INSERT INTO " + tableName + "(" + fields.toString() + ") VALUES(" + params.toString() + ")";
		return sql;
	}

	@Override
	public List<T> fillAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
