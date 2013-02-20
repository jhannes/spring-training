package com.exilesoft.exercise.infrastructure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

public class AbstractInmemoryRepository {

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

    protected void generateId(Object object) {
        try {
            Field field = object.getClass().getDeclaredField("id");
            field.setAccessible(true);
            field.set(object, idSequence++);
        } catch (NoSuchFieldException|SecurityException|IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
