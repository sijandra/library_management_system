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

@RestController
@RequestMapping(path = "api/lms/user")
public class UsersService {
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final GetByUserIdUseCase getByUserIdUseCase;
    private final JwtService jwtService;

    @Autowired
    public UsersService(GetAllUsersUseCase getAllUsersUseCase, CreateUserUseCase createUserUseCase,
            LoginUserUseCase loginUserUseCase, GetByUserIdUseCase getByUserIdUseCase, JwtService jwtService) {
        this.getAllUsersUseCase = getAllUsersUseCase;
        this.createUserUseCase = createUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
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
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = this.createUserUseCase.execute(user);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");
        String userToken = this.loginUserUseCase.execute(email, password);

        if (userToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        return new ResponseEntity<>(userToken, HttpStatus.OK);
    }
}
