package com.exilesoft.exercise.infrastructure;

import java.util.List;

public interface Repository<T> {

	List<T> list();

	void create(T newObject);

	void update(T object);

	T find(Long id);

}