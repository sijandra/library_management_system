package com.library.library_management_system.modules.borrow.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.library.library_management_system.modules.borrow.domain.models.Borrow;
import com.library.library_management_system.modules.borrow.persistence.repository.BorrowRepository;

public class GetBorrowByPaidUseCase {
    private final BorrowRepository borrowRepository;

    @Autowired
    public GetBorrowByPaidUseCase(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public List<Borrow> execute() {
        return this.borrowRepository.getBorrowByPaid();
    }
}
