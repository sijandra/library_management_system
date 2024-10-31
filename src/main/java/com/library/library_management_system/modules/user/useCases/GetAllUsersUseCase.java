package com.library.library_management_system.modules.user.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.user.domain.models.User;
import com.library.library_management_system.modules.user.persistence.repository.UserRepository;

@Component
public class GetAllUsersUseCase {
    private final UserRepository userRepository;

    @Autowired
    public GetAllUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> execute() {
        return this.userRepository.getAllUsers();
    }
}
