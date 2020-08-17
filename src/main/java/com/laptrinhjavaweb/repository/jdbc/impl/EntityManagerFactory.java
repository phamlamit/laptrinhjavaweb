package com.laptrinhjavaweb.repository.jdbc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EntityManagerFactory {

	private static final EntityManagerFactory instance = new EntityManagerFactory();
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/javaweb62020module1";
	static final String USER = "root";
	static final String PASS = "12345";

	// private constructor to avoid client applications to use constructor
	private EntityManagerFactory() {
	}

	public static EntityManagerFactory getInstance() {
		return instance;
	}

	public Connection getConnection() {
		try {
			Class.forName(JDBC_DRIVER);
			return DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			
			return null;
		}

	}

}
