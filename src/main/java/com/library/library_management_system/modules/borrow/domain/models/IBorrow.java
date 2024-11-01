package com.library.library_management_system.modules.borrow.domain.models;

public interface IBorrow {
    Long getId();

    void setId(Long bookId);

    Long getBookId();

    void setBookId(Long bookId);

    Long getBorrowerUserId();

    void setBorrowerUserId(Long borrowerUserId);

    String getReturnDate();

    void setReturnDate(String returnDate);

    String getBorrowDate();

    void setBorrowDate(String borrowDate);

    String getBorrowStatus();

    void setBorrowStatus(String borrowStatus);
}
