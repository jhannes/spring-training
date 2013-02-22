package com.exilesoft.exercise.company;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.exilesoft.exercise.company.type.CompanyType;
import com.google.common.base.Objects;

@Entity
public class Company implements Serializable {


    private String companyName;
    private String companyUrl;

    private CompanyType companyType;

    @Id
    @GeneratedValue
    private Long id;

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

    @Override
	public int hashCode() {
		return Objects.hashCode(companyName, companyUrl);
    }

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Company))
			return false;
		Company other = (Company) obj;
		return Objects.equal(this.companyName, other.companyName)
				&& Objects.equal(this.companyUrl, other.companyUrl);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("companyName", companyName).add("companyUrl", companyUrl)
				.add("companyType", companyType)
				.toString();
	}
}
