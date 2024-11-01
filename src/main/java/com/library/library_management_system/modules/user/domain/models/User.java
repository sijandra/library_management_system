package com.library.library_management_system.modules.user.domain.models;

import java.time.LocalDate;

public class User implements IUser {
    private Long id;
    private String email;
    private String name;
    private Integer age;
    private String location;
    private String role;
    private String contactNumber;
    private String password;
    private Long[] booksBorrowed;
    private LocalDate createdAt;

    protected User(Long id, String email, String name, Integer age, String location, String role, String contactNumber,
            String password, Long[] booksBorrowed, LocalDate createdAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.age = age;
        this.location = location;
        this.role = role;
        this.contactNumber = contactNumber;
        this.password = password;
        this.booksBorrowed = booksBorrowed;
        this.createdAt = createdAt;
    }

    public static User create(Long id, String email, String name, Integer age, String location, String role,
            String contactNumber,
            String password, Long[] booksBorrowed, LocalDate createdAt) {
        return new User(id, email, name, age, location, role, contactNumber, password, booksBorrowed, createdAt);
    }

    @SuppressWarnings("unused")
    public static User createWithDefaults(Long id, String email, String name, Integer age, String location, String role,
            String contactNumber,
            String password) {

        return create(id, email, name, age, location, role, contactNumber, password,
                new Long[0],
                LocalDate.now());
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getContactNumber() {
        return contactNumber;
    }

    @Override
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Long[] getBooksBorrowed() {
        return booksBorrowed;
    }

    @Override
    public void setBooksBorrowed(Long[] booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }

    @Override
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}