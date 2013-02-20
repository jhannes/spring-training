package com.exilesoft.exercise.company;

import java.io.Serializable;

public class Company implements Serializable {

    private long idSequence;

    private String companyName;
    private String companyUrl;

    private CompanyType companyType;
    private final Long id = idSequence++;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public Long getId() {
        return id;
    }

    public int getEmployeeCount() {
        return 0;
    }

}
