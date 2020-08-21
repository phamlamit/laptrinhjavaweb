package com.laptrinhjavaweb.repository.jdbc;

public interface SimpleJpaRepository<T> {
	Long save(T t);

}
