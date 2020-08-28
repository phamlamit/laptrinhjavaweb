package com.laptrinhjavaweb.repository.jdbc;

import java.util.List;

public interface JpaRepository<T> {
	Long save(T t);
	List<T> fillAll();
	List<T> fillAll(String sql);
	T findById(long id);
	void delete(Long id);
	Long update(Long id,T t);
}
