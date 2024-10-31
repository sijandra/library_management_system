package com.library.library_management_system.modules.borrow.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.library.library_management_system.modules.borrow.persistence.mappers.BorrowSchema;

@Repository
public interface IBorrowRepository extends JpaRepository<BorrowSchema, Long> {

    BorrowSchema getBorrowById(Long id);

    List<BorrowSchema> getBorrowByUserId(Long userId);

    List<BorrowSchema> getBorrowByIsFinePaidFalse();

    List<BorrowSchema> getBorrowByIsLostTrue();

    List<BorrowSchema> getBorrowByBorrowStatus(String status);

    List<BorrowSchema> getBorrowByIsFinePaidTrue();

    default BorrowSchema createBorrowByBookId(BorrowSchema borrow) {
        return save(borrow);
    }

    default BorrowSchema updateBorrowById(Long id, BorrowSchema borrow) {
        borrow.setId(id);
        return save(borrow);
    }
}
