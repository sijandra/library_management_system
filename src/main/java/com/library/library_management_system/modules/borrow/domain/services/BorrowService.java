package com.library.library_management_system.modules.borrow.domain.services;

import static java.lang.Long.parseLong;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library_management_system.modules.borrow.domain.models.Borrow;
import com.library.library_management_system.modules.borrow.useCases.CreateBorrowUseCase;
import com.library.library_management_system.modules.borrow.useCases.GetBorrowByIdUseCase;
import com.library.library_management_system.modules.borrow.useCases.GetBorrowByStatusUseCase;
import com.library.library_management_system.modules.borrow.useCases.UpdateStatusByBorrowIdUseCase;
import com.library.library_management_system.modules.user.domain.services.JwtService;

@RestController
@RequestMapping(path = "api/lms/borrow")
public class BorrowService {
    private final CreateBorrowUseCase createBorrowUseCase;
    private final GetBorrowByIdUseCase getBorrowByIdUseCase;
    private final GetBorrowByStatusUseCase getBorrowByStatusUseCase;
    private final UpdateStatusByBorrowIdUseCase updateStatusByBorrowIdUseCase;
    private final JwtService jwtService;

    @Autowired
    public BorrowService(
            CreateBorrowUseCase createBorrowUseCase,
            GetBorrowByIdUseCase getBorrowByIdUseCase,
            GetBorrowByStatusUseCase getBorrowByStatusUseCase,
            UpdateStatusByBorrowIdUseCase updateStatusByBorrowIdUseCase,
            JwtService jwtService) {
        this.createBorrowUseCase = createBorrowUseCase;
        this.getBorrowByIdUseCase = getBorrowByIdUseCase;
        this.getBorrowByStatusUseCase = getBorrowByStatusUseCase;
        this.updateStatusByBorrowIdUseCase = updateStatusByBorrowIdUseCase;
        this.jwtService = jwtService;
    }

    @PostMapping("/create")
    public ResponseEntity<Borrow> createBorrow(@RequestHeader() String token, @RequestBody Borrow borrow) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);

        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        borrow.setBorrowerUserId(parseLong(userId));

        Borrow createdBorrow = this.createBorrowUseCase.execute(borrow);
        return new ResponseEntity<>(createdBorrow, HttpStatus.CREATED);
    }

    @GetMapping("/get-borrow/{id}")
    public ResponseEntity<Borrow> getBorrowById(@RequestHeader() String token, @PathVariable Long id) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);

        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Borrow borrow = this.getBorrowByIdUseCase.execute(id);
        return borrow != null ? ResponseEntity.ok(borrow) : ResponseEntity.notFound().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Borrow>> getBorrowByStatus(@RequestHeader() String token, @PathVariable String status) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);

        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Borrow> borrows = this.getBorrowByStatusUseCase.execute(status);
        return ResponseEntity.ok(borrows);
    }

    @PatchMapping("/update-status/{id}")
    public ResponseEntity<Borrow> updateBorrowById(@RequestHeader() String token,
            @PathVariable Long id,
            @RequestBody BorrowStatusUpdateRequest borrowStatusUpdateRequest) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);

        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String borrowStatus = borrowStatusUpdateRequest.getBorrowStatus().trim();
        Borrow updatedBorrow = this.updateStatusByBorrowIdUseCase.execute(id, borrowStatus);
        return ResponseEntity.ok(updatedBorrow);
    }

    public static class BorrowStatusUpdateRequest {
        private String borrowStatus;

        public String getBorrowStatus() {
            return borrowStatus;
        }

        public void setBorrowStatus(String borrowStatus) {
            this.borrowStatus = borrowStatus;
        }
    }
}
