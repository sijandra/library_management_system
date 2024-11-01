package com.library.library_management_system.modules.user.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.modules.user.persistence.mappers.UserSchema;

@Repository
public interface IUserRepository extends JpaRepository<UserSchema, String> {
    UserSchema findByEmail(String email);
    UserSchema findById(Long id);
}