package com.h2rd.refactoring.usermanagement.model;

import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class User {

    @Email
    private String email;
    @NotNull
    private String name;
    @NotNull
    private List<String> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(final List<String> roles) {
        this.roles = roles;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(name, user.name) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, roles);
    }
}