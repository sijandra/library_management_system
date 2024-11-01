package com.library.library_management_system.modules.borrow.persistence.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.modules.borrow.domain.models.Borrow;
import com.library.library_management_system.modules.borrow.persistence.mappers.BorrowMapper;
import com.library.library_management_system.modules.borrow.persistence.mappers.BorrowSchema;

@Repository
public class BorrowRepository {
    private final IBorrowRepository borrowRepository;

    @Autowired
    public BorrowRepository(IBorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public Borrow getBorrowById(Long id) {
        BorrowSchema borrowSchema = borrowRepository.findById(id).orElse(null);
        return BorrowMapper.toDomain(borrowSchema);
    }

    public List<Borrow> getBorrowByStatus(String status) {
        List<BorrowSchema> borrowSchemas = borrowRepository.getBorrowByBorrowStatus(status);
        return borrowSchemas.stream()
                .map(BorrowMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Borrow createBorrow(Borrow borrow) {
        BorrowSchema borrowSchema = BorrowMapper.toPersistence(borrow);
        BorrowSchema savedBorrowSchema = borrowRepository.save(borrowSchema);
        return BorrowMapper.toDomain(savedBorrowSchema);
    }

    public Borrow updateStatusByBorrowById(Long id, String borrowStatus) {
        BorrowSchema foundBorrowSchema = borrowRepository.findById(id).orElse(null);

        foundBorrowSchema.setBorrowStatus(borrowStatus);

        BorrowSchema updatedBorrowSchema = borrowRepository.save(foundBorrowSchema);
        return BorrowMapper.toDomain(updatedBorrowSchema);
    }
}
