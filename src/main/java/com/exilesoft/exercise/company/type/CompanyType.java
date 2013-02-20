package com.exilesoft.exercise.company.type;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.common.base.Objects;

@Entity
public class CompanyType implements Serializable {

    private static long idSequence;

    @Id
    private final Long id = idSequence++;

    private String typeName;

    CompanyType() {
    }

    public CompanyType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(typeName);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CompanyType))
            return false;
        CompanyType other = (CompanyType) obj;
        return Objects.equal(this.id, other.id) && Objects.equal(this.typeName, other.typeName);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("id", id).add("typeName", typeName).toString();
    }
}
