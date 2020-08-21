package com.laptrinhjavaweb.repository.jdbc.impl;

import java.lang.annotation.Target;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.repository.jdbc.SimpleJpaRepository;

public class SimpleJpaRepositoryImpl<T> implements SimpleJpaRepository<T>{

	@Override
	public Long save(Object object) {
		
		
		String sql = buildSqlInsert();
		return null;
	}

	private String buildSqlInsert() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType p = (ParameterizedType)t;
		Class<T> tClass = (Class<T>) p.getActualTypeArguments()[0];
		String tableName = "";
		if(tClass.isAnnotationPresent(Target.class)) {
			Table table = tClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		String sql = "INSERT INTO " + tableName;
		return null;
	}

}
