package com.library.library_management_system.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.user.persistence.repository.UserRepository;

@Component
public class LogoutUserUseCase {
    private final UserRepository userRepository;

    @Autowired
    public LogoutUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String execute(String token) {
        return this.userRepository.logoutUser(token);
    }
}
