package com.library.library_management_system.modules.borrow.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.borrow.domain.models.Borrow;
import com.library.library_management_system.modules.borrow.persistence.repository.BorrowRepository;

@Component
public class UpdateBorrowByIdUseCase {
    private final BorrowRepository borrowRepository;

    @Autowired
    public UpdateBorrowByIdUseCase(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public Borrow execute(Long id, Borrow borrow) {
        return this.borrowRepository.updateBorrowById(id, borrow);
    }
}
