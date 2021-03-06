package com.exilesoft.exercise.company.type;

import java.util.List;

public interface CompanyTypeRepository {

	List<CompanyType> list();

	void create(CompanyType newObject);

	CompanyType find(Long id);

}
