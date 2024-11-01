package com.library.library_management_system.modules.borrow.domain.services;

import java.util.List;

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

import com.library.library_management_system.modules.borrow.domain.models.Borrow;
import com.library.library_management_system.modules.borrow.useCases.CreateBorrowUseCase;
import com.library.library_management_system.modules.borrow.useCases.GetBorrowByIdUseCase;
import com.library.library_management_system.modules.borrow.useCases.GetBorrowByLostUseCase;
import com.library.library_management_system.modules.borrow.useCases.GetBorrowByNotPaidUseCase;
import com.library.library_management_system.modules.borrow.useCases.GetBorrowByPaidUseCase;
import com.library.library_management_system.modules.borrow.useCases.GetBorrowByStatusUseCase;
import com.library.library_management_system.modules.borrow.useCases.GetBorrowByUserIdUseCase;
import com.library.library_management_system.modules.borrow.useCases.UpdateBorrowByIdUseCase;
import com.library.library_management_system.modules.user.domain.services.JwtService;

@RestController
@RequestMapping(path = "api/lms/borrow")
public class BorrowService {
    private final CreateBorrowUseCase createBorrowUseCase;
    private final GetBorrowByIdUseCase getBorrowByIdUseCase;
    private final GetBorrowByUserIdUseCase getBorrowByUserIdUseCase;
    private final GetBorrowByNotPaidUseCase getBorrowByNotPaidUseCase;
    private final GetBorrowByPaidUseCase getBorrowByPaidUseCase;
    private final GetBorrowByLostUseCase getBorrowByLostUseCase;
    private final GetBorrowByStatusUseCase getBorrowByStatusUseCase;
    private final UpdateBorrowByIdUseCase updateBorrowByIdUseCase;
    private final JwtService jwtService;

    @Autowired
    public BorrowService(
            CreateBorrowUseCase createBorrowUseCase,
            GetBorrowByIdUseCase getBorrowByIdUseCase,
            GetBorrowByUserIdUseCase getBorrowByUserIdUseCase,
            GetBorrowByNotPaidUseCase getBorrowByNotPaidUseCase,
            GetBorrowByPaidUseCase getBorrowByPaidUseCase,
            GetBorrowByStatusUseCase getBorrowByStatusUseCase,
            GetBorrowByLostUseCase getBorrowByLostUseCase,
            UpdateBorrowByIdUseCase updateBorrowByIdUseCase,
            JwtService jwtService) {
        this.createBorrowUseCase = createBorrowUseCase;
        this.getBorrowByIdUseCase = getBorrowByIdUseCase;
        this.getBorrowByUserIdUseCase = getBorrowByUserIdUseCase;
        this.getBorrowByNotPaidUseCase = getBorrowByNotPaidUseCase;
        this.getBorrowByPaidUseCase = getBorrowByPaidUseCase;
        this.getBorrowByLostUseCase = getBorrowByLostUseCase;
        this.getBorrowByStatusUseCase = getBorrowByStatusUseCase;
        this.updateBorrowByIdUseCase = updateBorrowByIdUseCase;
        this.jwtService = jwtService;
    }

    @PostMapping("/create")
    public ResponseEntity<Borrow> createBorrow(@RequestHeader() String token, @RequestBody Borrow borrow) {
        if (!isValidToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Borrow createdBorrow = this.createBorrowUseCase.execute(borrow);
        return new ResponseEntity<>(createdBorrow, HttpStatus.CREATED);
    }

    @GetMapping("/get-borrow/{id}")
    public ResponseEntity<Borrow> getBorrowById(@RequestHeader() String token, @PathVariable Long id) {
        if (!isValidToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Borrow borrow = this.getBorrowByIdUseCase.execute(id);
        return borrow != null ? ResponseEntity.ok(borrow) : ResponseEntity.notFound().build();
    }

    @GetMapping("/get-by-user/{userId}")
    public ResponseEntity<List<Borrow>> getBorrowByUserId(@RequestHeader() String token, @PathVariable Long userId) {
        if (!isValidToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Borrow> borrows = this.getBorrowByUserIdUseCase.execute(userId);
        return ResponseEntity.ok(borrows);
    }

    @GetMapping("/not-paid")
    public ResponseEntity<List<Borrow>> getBorrowByNotPaid(@RequestHeader() String token) {
        if (!isValidToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Borrow> borrows = this.getBorrowByNotPaidUseCase.execute();
        return ResponseEntity.ok(borrows);
    }

    @GetMapping("/lost")
    public ResponseEntity<List<Borrow>> getBorrowByLost(@RequestHeader() String token) {
        if (!isValidToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Borrow> borrows = this.getBorrowByLostUseCase.execute();
        return ResponseEntity.ok(borrows);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Borrow>> getBorrowByStatus(@RequestHeader() String token, @PathVariable String status) {
        if (!isValidToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Borrow> borrows = this.getBorrowByStatusUseCase.execute(status);
        return ResponseEntity.ok(borrows);
    }

    @GetMapping("/paid")
    public ResponseEntity<List<Borrow>> getBorrowByPaid(@RequestHeader() String token) {
        if (!isValidToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Borrow> borrows = this.getBorrowByPaidUseCase.execute();
        return ResponseEntity.ok(borrows);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Borrow> updateBorrowById(@RequestHeader() String token, @PathVariable Long id,
            @RequestBody Borrow borrow) {
        if (!isValidToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Borrow updatedBorrow = this.updateBorrowByIdUseCase.execute(id, borrow);
        return ResponseEntity.ok(updatedBorrow);
    }

    private boolean isValidToken(String token) {
        if (token == null)
            return false;
        String userId = jwtService.extractMetadata(token);
        return jwtService.validateToken(token, userId);
    }
}
