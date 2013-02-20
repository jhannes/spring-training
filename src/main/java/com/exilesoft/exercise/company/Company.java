package com.exilesoft.exercise.company;

import java.io.Serializable;

import com.exilesoft.exercise.company.type.CompanyType;

public class Company implements Serializable {

    private static long idSequence;

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
