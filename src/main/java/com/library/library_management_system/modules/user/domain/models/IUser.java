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

    Long[] getBooksBorrowed();

    LocalDate getCreatedAt();

    void setId(Long id);

    void setEmail(String email);

    void setName(String name);

    void setAge(Integer age);

    void setLocation(String location);

    void setRole(String role);

    void setContactNumber(String contactNumber);

    void setPassword(String password);

    void setBooksBorrowed(Long[] booksBorrowed);

    void setCreatedAt(LocalDate createdAt);
}
