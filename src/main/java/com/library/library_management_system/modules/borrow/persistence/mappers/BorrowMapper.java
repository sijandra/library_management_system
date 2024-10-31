package com.library.library_management_system.modules.borrow.persistence.mappers;

import com.library.library_management_system.modules.borrow.domain.models.Borrow;

public class BorrowMapper {
    private BorrowMapper() {
    }

    public static Borrow toDomain(BorrowSchema borrowSchema) {
        if (borrowSchema == null) {
            return null;
        }
        return Borrow.create(
                borrowSchema.getId(),
                borrowSchema.getBookId(),
                borrowSchema.getBorrowerUserId(),
                borrowSchema.getReturnDate(),
                borrowSchema.getBorrowDate(),
                borrowSchema.getBorrowStatus(),
                borrowSchema.getFineFee(),
                borrowSchema.getIsFinePaid(),
                borrowSchema.getIsLost(),
                borrowSchema.getFinePerDay());
    }

    public static BorrowSchema toPersistence(Borrow borrowDomain) {
        if (borrowDomain == null) {
            return null;
        }

        BorrowSchema borrowSchema = new BorrowSchema();
        borrowSchema.setId(borrowDomain.getId());
        borrowSchema.setBookId(borrowDomain.getBookId());
        borrowSchema.setBorrowerUserId(borrowDomain.getBorrowerUserId());
        borrowSchema.setReturnDate(borrowDomain.getReturnDate());
        borrowSchema.setBorrowDate(borrowDomain.getBorrowDate());
        borrowSchema.setBorrowStatus(borrowDomain.getBorrowStatus());
        borrowSchema.setFineFee(borrowDomain.getFineFee());
        borrowSchema.setIsFinePaid(borrowDomain.getIsFinePaid());
        borrowSchema.setIsLost(borrowDomain.getIsLost());
        borrowSchema.setFinePerDay(borrowDomain.getFinePerDay());

        return borrowSchema;
    }
}
