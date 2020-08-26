package com.laptrinhjavaweb.repository.jdbc;

import java.util.List;

public interface SimpleJpaRepository<T> {
	Long save(T t);
	List<T> fillAll();
	T findById(long id);

}
