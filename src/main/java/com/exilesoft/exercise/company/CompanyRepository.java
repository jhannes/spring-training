package com.exilesoft.exercise.company;

import java.util.List;

import com.exilesoft.exercise.infrastructure.Repository;

public interface CompanyRepository extends Repository<Company> {

	List<Company> findByName(String nameQuery);

}
