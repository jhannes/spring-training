package com.exilesoft.exercise.infrastructure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractInmemoryRepository<T> implements Repository<T> {

    private final Map<Long, T> entities = new HashMap<>();

    @Override
    public void create(T newObject) {
        entities.put(generateId(newObject), newObject);
    }

    @Override
    public List<T> list() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public void update(T object) {
    	entities.put(getId(object), object);
    }

	@Override
    public T find(Long id) {
        return clone(entities.get(id));
    }

    private long idSequence = 0;

	@SuppressWarnings("unchecked")
	protected static<T> T clone(T obj) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream oout = new ObjectOutputStream(out);
			oout.writeObject(obj);
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(
					out.toByteArray()));
			return (T)in.readObject();
		} catch (ClassNotFoundException|IOException e) {
			throw new RuntimeException(e);
		}
	}

    protected Long generateId(T object) {
        try {
            Field field = object.getClass().getDeclaredField("id");
            field.setAccessible(true);
            long id = idSequence++;
			field.set(object, id);
			return id;
        } catch (NoSuchFieldException|SecurityException|IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected Long getId(T object) {
        try {
            Field field = object.getClass().getDeclaredField("id");
            field.setAccessible(true);
			return (Long) field.get(object);
        } catch (NoSuchFieldException|SecurityException|IllegalAccessException e) {
            throw new RuntimeException(e);
        }
	}



}
