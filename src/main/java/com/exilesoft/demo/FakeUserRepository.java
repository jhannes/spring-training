package com.exilesoft.demo;

import javax.inject.Named;

import com.exilesoft.demo.security.RequiresLogin;


public class FakeUserRepository implements UserRepository {

    private final String password;
	private final String username;

    public FakeUserRepository(@Named("username") String username, @Named("password") String password) {
        this.username = username;
		this.password = password;
    }

    @Override
	@RequiresLogin("Johannes")
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
    	return username;
    }

}