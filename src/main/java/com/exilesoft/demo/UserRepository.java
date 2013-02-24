package com.exilesoft.demo;

import com.exilesoft.demo.security.RequiresLogin;

public interface UserRepository {

	@RequiresLogin("Johannes")
    String getPassword();

	String getUsername();
}