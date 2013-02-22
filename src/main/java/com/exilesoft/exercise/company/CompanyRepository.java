package com.exilesoft.exercise.company;

import java.util.List;

public interface CompanyRepository {

	void create(Company company);

	List<Company> list();

	Company find(Long id);

}
