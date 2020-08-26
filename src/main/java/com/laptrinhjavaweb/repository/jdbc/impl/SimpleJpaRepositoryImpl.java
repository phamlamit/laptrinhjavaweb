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
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.repository.jdbc.SimpleJpaRepository;
import com.laptrinhjavaweb.util.ResultSetMapper;

public class SimpleJpaRepositoryImpl<T> implements SimpleJpaRepository<T> {

	private Class<T> zClass;

	public SimpleJpaRepositoryImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType p = (ParameterizedType) t;
		Class<T> zClass = (Class<T>) p.getActualTypeArguments()[0];
	}

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
			int index = 1;
			for (Field feild : object.getClass().getDeclaredFields()) {
				feild.setAccessible(true);
				rs.setObject(index, feild.get(object));
				index++;
			}
			rs.executeUpdate();
			conn.commit();
			result = rs.getGeneratedKeys();

			if (result.next()) {
				buildingId = result.getLong(1);
			}

		} catch (SQLException | IllegalAccessException e) {
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

	private String buildSqlInsert() {
		
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
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
			if (field.isAnnotationPresent(Column.class)) {
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
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		List<T> result = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			conn = EntityManagerFactory.getInstance().getConnection();

			stmt = conn.createStatement();
			
			String tableName = "";
			if (zClass.isAnnotationPresent(Table.class)) {
				Table table = zClass.getAnnotation(Table.class);
				tableName = table.name();
			}

			String sql = "SELECT * FROM " + tableName + " b where 1=1";
			rs = stmt.executeQuery(sql);
			return resultSetMapper.mapRow(rs, this.zClass);

		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
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

	@Override
	public T findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> fillAll(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

}
