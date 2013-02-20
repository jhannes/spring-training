package com.exilesoft.exercise.company;

import java.util.List;

public interface CompanyRepository {

    List<Company> list();

    void create(Company newObject);

    Company find(Long id);

}
