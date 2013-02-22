package com.exilesoft.demo;


public class Application {

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