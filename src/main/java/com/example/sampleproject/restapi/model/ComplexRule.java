package com.example.sampleproject.restapi.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@DiscriminatorValue("complex")
public class ComplexRule extends AbstractRule {

    @Column(name = "privileges")
    private String privileges;
    @Column(name = "nationality")
    private String nationality  = "'BG'";

    public ComplexRule() {
    }

    public ComplexRule(int id, String name, String privileges, String field) {
        this.id = id;
        this.name = name;
        this.privileges = privileges;
        this.field = field;
    }

    @Override
    public String execute(int parameter1, int parameter2) {
        // no string but query return, would be proper
        if (privileges.equals("admin"))
            return "SELECT u FROM UserEntity u WHERE u." + field + " > " + parameter1 + " AND u." + field + " < " + parameter2 + " AND u.nationality =" + nationality;
        return "SELECT u FROM UserEntity u WHERE u." + field + " > " + parameter1 + " AND u." + field + " < " + parameter2;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexRule that)) return false;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(privileges, that.privileges) && Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, privileges, field);
    }

}