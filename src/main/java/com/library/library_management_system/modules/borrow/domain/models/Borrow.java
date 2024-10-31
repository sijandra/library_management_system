package com.library.library_management_system.modules.borrow.domain.models;

public class Borrow implements IBorrow {
    private Long id;
    private Long bookId;
    private Long borrowerUserId;
    private String returnDate;
    private String borrowDate;
    private String borrowStatus;
    private Double fineFee;
    private Boolean isFinePaid;
    private Boolean isLost;
    private Double finePerDay;

    protected Borrow(Long id, Long bookId, Long borrowerUserId, String returnDate, String borrowDate,
            String borrowStatus,
            Double fineFee, Boolean isFinePaid, Boolean isLost, Double finePerDay) {
        this.id = id;
        this.bookId = bookId;
        this.borrowerUserId = borrowerUserId;
        this.returnDate = returnDate;
        this.borrowDate = borrowDate;
        this.borrowStatus = borrowStatus;
        this.fineFee = fineFee;
        this.isFinePaid = isFinePaid;
        this.isLost = isLost;
        this.finePerDay = finePerDay;
    }

    public static Borrow create(Long id, Long bookId, Long borrowerUserId, String returnDate, String borrowDate,
            String borrowStatus, Double fineFee, Boolean isFinePaid, Boolean isLost,
            Double finePerDay) {
        return new Borrow(id, bookId, borrowerUserId, returnDate, borrowDate, borrowStatus, fineFee, isFinePaid, isLost,
                finePerDay);
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

    @Override
    public Double getFineFee() {
        return fineFee;
    }

    @Override
    public void setFineFee(Double fineFee) {
        this.fineFee = fineFee;
    }

    @Override
    public Boolean getIsFinePaid() {
        return isFinePaid;
    }

    @Override
    public void setIsFinePaid(Boolean isFinePaid) {
        this.isFinePaid = isFinePaid;
    }

    @Override
    public Boolean getIsLost() {
        return isLost;
    }

    @Override
    public void setIsLost(Boolean isLost) {
        this.isLost = isLost;
    }

    @Override
    public Double getFinePerDay() {
        return finePerDay;
    }

    @Override
    public void setFinePerDay(Double finePerDay) {
        this.finePerDay = finePerDay;
    }
}
