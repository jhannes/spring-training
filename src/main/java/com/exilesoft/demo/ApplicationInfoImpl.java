package com.exilesoft.demo;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exilesoft.demo.security.RequiresLogin;


@Component
public class ApplicationInfoImpl implements ApplicationInfo {

    private final String password;
	private final String username;

	@Autowired
    public ApplicationInfoImpl(@Named("username") String username, @Named("password") String password) {
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