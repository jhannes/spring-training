package com.exilesoft.demo;

import org.springframework.beans.factory.annotation.Autowired;


public class UserManager {

    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getPassword() {
        return userRepository.getPassword();
    }

	public String getUsername() {
		return userRepository.getUsername();
	}

}