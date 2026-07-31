package com.rev.ers.model;

import com.rev.ers.enums.Role;

import java.util.Objects;

public abstract class User {
    private int user_id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private int department_id;

    private User() {}

    public User(int user_id, String username, String password, String firstName, String lastName, Role role, int department_id) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.department_id = department_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", department_id=" + department_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return getUser_id() == user.getUser_id() &&
                getDepartment_id() == user.getDepartment_id() &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getUsername(), getPassword(),
                getFirstName(), getLastName(), getRole(), getDepartment_id());
    }
}
