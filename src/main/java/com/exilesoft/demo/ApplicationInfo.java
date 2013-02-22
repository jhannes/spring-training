package com.exilesoft.demo;

import com.exilesoft.demo.security.RequiresLogin;

public interface ApplicationInfo {

	@RequiresLogin("Johannes")
    String getPassword();

	String getUsername();
}