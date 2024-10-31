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
    private String[] booksBorrowed;
    private String[] lostBooks;
    private LocalDate createdAt;

    protected User(Long id, String email, String name, Integer age, String location, String role, String contactNumber,
            String password, String[] booksBorrowed, String[] lostBooks, LocalDate createdAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.age = age;
        this.location = location;
        this.role = role;
        this.contactNumber = contactNumber;
        this.password = password;
        this.booksBorrowed = booksBorrowed;
        this.lostBooks = lostBooks;
        this.createdAt = createdAt;
    }

    public static User create(Long id, String email, String name, Integer age, String location, String role,
            String contactNumber,
            String password, String[] booksBorrowed, String[] lostBooks, LocalDate createdAt) {
        return new User(id, email, name, age, location, role, contactNumber, password, booksBorrowed, lostBooks,
                createdAt);
    }

    @SuppressWarnings("unused")
    public static User createWithDefaults(Long id, String email, String name, Integer age, String location, String role,
            String contactNumber,
            String password) {

        return create(id, email, name, age, location, role, contactNumber, password,
                new String[0],
                new String[0],
                LocalDate.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getBooksBorrowed() {
        return booksBorrowed;
    }

    public void setBooksBorrowed(String[] booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }

    public String[] getLostBooks() {
        return lostBooks;
    }

    public void setLostBooks(String[] lostBooks) {
        this.lostBooks = lostBooks;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}