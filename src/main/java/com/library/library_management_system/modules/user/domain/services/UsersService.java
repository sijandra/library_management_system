package com.library.library_management_system.modules.user.domain.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library_management_system.modules.user.domain.models.User;
import com.library.library_management_system.modules.user.useCases.CreateUserUseCase;
import com.library.library_management_system.modules.user.useCases.GetAllUsersUseCase;
import com.library.library_management_system.modules.user.useCases.GetByUserIdUseCase;
import com.library.library_management_system.modules.user.useCases.LoginUserUseCase;
import com.library.library_management_system.modules.user.useCases.LogoutUserUseCase;

@RestController
@RequestMapping(path = "api/lms/user")
public class UsersService {
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final LogoutUserUseCase logoutUserUseCase;
    private final GetByUserIdUseCase getByUserIdUseCase;
    private final JwtService jwtService;

    @Autowired
    public UsersService(GetAllUsersUseCase getAllUsersUseCase, CreateUserUseCase createUserUseCase,
            LoginUserUseCase loginUserUseCase, LogoutUserUseCase logoutUserUseCase,
            GetByUserIdUseCase getByUserIdUseCase, JwtService jwtService) {
        this.getAllUsersUseCase = getAllUsersUseCase;
        this.createUserUseCase = createUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
        this.logoutUserUseCase = logoutUserUseCase;
        this.getByUserIdUseCase = getByUserIdUseCase;
        this.jwtService = jwtService;
    }

    @GetMapping
    public String demoUser() {
        return "User is good";
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader() String token) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);

        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<User> users = this.getAllUsersUseCase.execute();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/get-user-by-id/{id}")
    public ResponseEntity<User> getByUserId(@RequestHeader() String token, @PathVariable Long id) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);

        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = this.getByUserIdUseCase.execute(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User createdUser = this.createUserUseCase.execute(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("data", createdUser));
        } catch (Exception e) {
            Map<String, Object> errorResponse = Map.of(
                    "message", Map.of(
                            "message", "An error occurred while creating the user",
                            "details", e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
        try {
            String email = loginData.get("email");
            String password = loginData.get("password");
            Object[] loginResponse = this.loginUserUseCase.execute(email, password);

            if (loginResponse == null) {
                Map<String, Object> errorResponse = Map.of(
                        "data", Map.of(
                                "message", "Invalid credentials"));
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }

            String token = (String) loginResponse[0];
            String role = (String) loginResponse[1];
            Long userId = (Long) loginResponse[2];

            return ResponseEntity.ok(Map.of("data", Map.of("token", token, "role", role, "userId", userId)));
        } catch (Exception e) {
            Map<String, Object> errorResponse = Map.of(
                    "data", Map.of(
                            "message", "An error occurred during login",
                            "details", e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestHeader() String token) {
        return ResponseEntity.ok(this.logoutUserUseCase.execute(token));
    }
}
