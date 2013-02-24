package com.exilesoft.demo;


public class UserManager {

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