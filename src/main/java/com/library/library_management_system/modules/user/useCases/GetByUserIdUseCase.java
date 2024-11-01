package com.library.library_management_system.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.user.domain.models.User;
import com.library.library_management_system.modules.user.persistence.repository.UserRepository;

@Component
public class GetByUserIdUseCase {
    private final UserRepository userRepository;

    @Autowired
    public GetByUserIdUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(Long id) {
        return this.userRepository.getUserById(id);
    }
}
