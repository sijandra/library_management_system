package com.library.library_management_system.modules.user.domain.models;

import java.time.LocalDate;

public interface IUser {
    Long getId();

    String getEmail();

    String getName();

    Integer getAge();

    String getLocation();

    String getRole();

    String getContactNumber();

    String getPassword();

    String[] getBooksBorrowed();

    String[] getLostBooks();

    LocalDate getCreatedAt();

    void setId(Long id);

    void setEmail(String email);

    void setName(String name);

    void setAge(Integer age);

    void setLocation(String location);

    void setRole(String role);

    void setContactNumber(String contactNumber);

    void setPassword(String password);

    void setBooksBorrowed(String[] booksBorrowed);

    void setLostBooks(String[] lostBooks);

    void setCreatedAt(LocalDate createdAt);
}
