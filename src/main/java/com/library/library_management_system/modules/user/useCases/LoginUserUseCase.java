package com.library.library_management_system.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.user.persistence.repository.UserRepository;

@Component
public class LoginUserUseCase {
    private final UserRepository userRepository;

    @Autowired
    public LoginUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Object[] execute(String email, String password) {
        return this.userRepository.loginUser(email, password);
    }
}
