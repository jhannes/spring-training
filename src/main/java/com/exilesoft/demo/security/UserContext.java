package com.exilesoft.demo.security;

public class UserContext {

	static final ThreadLocal<String> user = new ThreadLocal<>();

	public static String getUser() {
		return user.get();
	}

	public static void setUser(String username) {
		user.set(username);
	}

}
