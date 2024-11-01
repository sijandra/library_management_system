package com.library.library_management_system.modules.borrow.domain.models;

public class Borrow implements IBorrow {
    private Long id;
    private Long bookId;
    private Long borrowerUserId;
    private String returnDate;
    private String borrowDate;
    private String borrowStatus;

    protected Borrow(Long id, Long bookId, Long borrowerUserId, String returnDate, String borrowDate,
            String borrowStatus) {
        this.id = id;
        this.bookId = bookId;
        this.borrowerUserId = borrowerUserId;
        this.returnDate = returnDate;
        this.borrowDate = borrowDate;
        this.borrowStatus = borrowStatus;
    }

    public static Borrow create(Long id, Long bookId, Long borrowerUserId, String returnDate, String borrowDate,
            String borrowStatus) {
        return new Borrow(id, bookId, borrowerUserId, returnDate, borrowDate, borrowStatus);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getBookId() {
        return bookId;
    }

    @Override
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public Long getBorrowerUserId() {
        return borrowerUserId;
    }

    @Override
    public void setBorrowerUserId(Long borrowerUserId) {
        this.borrowerUserId = borrowerUserId;
    }

    @Override
    public String getReturnDate() {
        return returnDate;
    }

    @Override
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String getBorrowDate() {
        return borrowDate;
    }

    @Override
    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Override
    public String getBorrowStatus() {
        return borrowStatus;
    }

    @Override
    public void setBorrowStatus(String borrowStatus) {
        this.borrowStatus = borrowStatus;
    }
}
