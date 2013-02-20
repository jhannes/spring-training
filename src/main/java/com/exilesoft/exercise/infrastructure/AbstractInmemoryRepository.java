package com.exilesoft.exercise.infrastructure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AbstractInmemoryRepository {

	@SuppressWarnings("unchecked")
	protected static<T> T clone(T obj) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream oout = new ObjectOutputStream(out);
			oout.writeObject(obj);
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(
					out.toByteArray()));
			return (T)in.readObject();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
