package com.epam.testingsystem.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.epam.testingsystem.domain.AbstractObject;

public abstract class AbstractRepository<T extends AbstractObject> {
	protected static int ID = 1;
	
	private Map<Integer, T> objects;
	
	public AbstractRepository() {
		objects = new HashMap<Integer, T>();
	}
	
	public void create(T object) {
		objects.put(object.getId(), object);
	}

	public void deleteById(int id) {
		T obj = objects.get(id);
		if (obj != null) {
			objects.remove(obj);
		}
	}

	public void update(T object) {
		AbstractObject obj = objects.get(object.getId());
		if (obj != null) {
			objects.put(object.getId(), object);
		}
	}
	
	public Collection<T> list() {
		return objects.values();
	}
}
