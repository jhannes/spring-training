package com.exilesoft.exercise.company;

import java.util.ArrayList;
import java.util.List;

import com.exilesoft.exercise.infrastructure.AbstractInmemoryRepository;

public class InmemoryCompanyRepository extends AbstractInmemoryRepository<Company> implements CompanyRepository {

	@Override
	public List<Company> findByName(String nameQuery) {
		List<Company> result = new ArrayList<>();
		for (Company company : list()) {
			if (company.getCompanyName().toLowerCase().contains(nameQuery.toLowerCase())) {
				result.add(company);
			}
		}
		return result;
	}


}
