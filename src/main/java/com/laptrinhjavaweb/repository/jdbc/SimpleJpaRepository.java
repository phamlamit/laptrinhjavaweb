package com.laptrinhjavaweb.repository.jdbc;

import java.util.List;

public interface SimpleJpaRepository<T> {
	Long save(T t);
	List<T> fillAll();
	List<T> fillAll(String sql);
	T findById(long id);

}
