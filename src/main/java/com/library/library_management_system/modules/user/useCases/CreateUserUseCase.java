package com.library.library_management_system.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.user.domain.models.User;
import com.library.library_management_system.modules.user.persistence.repository.UserRepository;

@Component
public class CreateUserUseCase {
    private final UserRepository userRepository;

    @Autowired
    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(User user) {
        return this.userRepository.createUser(user);
    }
}
