package com.exilesoft.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Application {

    @Autowired
    private ApplicationInfo applicationInfo;

    public void setApplicationInfo(ApplicationInfo applicationInfo) {
        this.applicationInfo = applicationInfo;
    }

    public String getPassword() {
        return applicationInfo.getPassword();
    }

	public String getUsername() {
		return applicationInfo.getUsername();
	}

}