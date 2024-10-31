package com.library.library_management_system.modules.borrow.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.borrow.domain.models.Borrow;
import com.library.library_management_system.modules.borrow.persistence.repository.BorrowRepository;

@Component
public class GetBorrowByLostUseCase {
    private final BorrowRepository borrowRepository;

    @Autowired
    public GetBorrowByLostUseCase(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public List<Borrow> execute() {
        return borrowRepository.getBorrowByLost();
    }
}