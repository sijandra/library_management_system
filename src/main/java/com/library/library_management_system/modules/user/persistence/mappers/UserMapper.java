package com.library.library_management_system.modules.user.persistence.mappers;

import com.library.library_management_system.modules.user.domain.models.User;

public class UserMapper {
    private UserMapper() {
    }

    public static User toDomain(UserSchema userSchema) {
        if (userSchema == null) {
            return null;
        }

        return User.create(
                userSchema.getId(),
                userSchema.getEmail(),
                userSchema.getName(),
                userSchema.getAge(),
                userSchema.getLocation(),
                userSchema.getRole(),
                userSchema.getContactNumber(),
                userSchema.getPassword(),
                userSchema.getBooksBorrowed(),
                userSchema.getLostBooks(),
                userSchema.getCreatedAt());
    }

    public static UserSchema toPersistence(User userDomain) {
        if (userDomain == null) {
            return null;
        }

        UserSchema userSchema = new UserSchema();
        userSchema.setId(userDomain.getId());
        userSchema.setEmail(userDomain.getEmail());
        userSchema.setName(userDomain.getName());
        userSchema.setAge(userDomain.getAge());
        userSchema.setLocation(userDomain.getLocation());
        userSchema.setRole(userDomain.getRole());
        userSchema.setContactNumber(userDomain.getContactNumber());
        userSchema.setPassword(userDomain.getPassword());
        userSchema.setBooksBorrowed(userDomain.getBooksBorrowed());
        userSchema.setLostBooks(userDomain.getLostBooks());
        userSchema.setCreatedAt(userDomain.getCreatedAt());

        return userSchema;
    }
}