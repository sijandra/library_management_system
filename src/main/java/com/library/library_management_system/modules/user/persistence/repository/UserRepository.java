package com.library.library_management_system.modules.user.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.modules.user.domain.models.User;
import com.library.library_management_system.modules.user.domain.services.JwtService;
import com.library.library_management_system.modules.user.persistence.mappers.UserMapper;
import com.library.library_management_system.modules.user.persistence.mappers.UserSchema;

@Repository
public class UserRepository {
    private final IUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public UserRepository(IUserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtService = jwtService;
    }

    public List<User> getAllUsers() {
        List<UserSchema> userSchemas = userRepository.findAll();
        List<User> userDomain = new ArrayList<>();

        for (UserSchema userSchema : userSchemas) {
            User user = UserMapper.toDomain(userSchema);
            userDomain.add(user);
        }

        return userDomain;
    }

    public User getUserByEmail(String email) {
        UserSchema userSchema = userRepository.findByEmail(email);
        return UserMapper.toDomain(userSchema);
    }

    public User createUser(User user) {
        String hashedPassword = this.passwordEncoder.encode(user.getPassword());

        User createdUserWithDefaults = User.createWithDefaults(user.getId(), user.getEmail(), user.getName(),
                user.getAge(),
                user.getLocation(), user.getRole(), user.getContactNumber(),
                hashedPassword);

        UserSchema userSchema = UserMapper.toPersistence(createdUserWithDefaults);

        UserSchema savedUserSchema = userRepository.save(userSchema);

        return UserMapper.toDomain(savedUserSchema);
    }

    public boolean validateUser(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    public String loginUser(String email, String password) {
        User user;

        if (email != null && !email.isEmpty()) {
            user = this.getUserByEmail(email);
        } else {
            return null;
        }

        if (user == null) {
            return null;
        }

        boolean isPasswordValid = validateUser(password, user.getPassword());
        if (!isPasswordValid) {
            return null;
        }

        return this.jwtService.generateToken(email);
    }
}
