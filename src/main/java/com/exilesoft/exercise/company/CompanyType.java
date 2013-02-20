package com.exilesoft.exercise.company;

import java.io.Serializable;

public class CompanyType implements Serializable {

    private static long idSequence;

    private final Long id = idSequence++;

    private final String typeName;

    public CompanyType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public Long getId() {
        return id;
    }

}
