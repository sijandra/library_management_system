package com.library.library_management_system.modules.borrow.persistence.mappers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrow")
public class BorrowSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "borrower_user_id", nullable = false)
    private Long borrowerUserId;

    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "borrow_date", nullable = false)
    private String borrowDate;

    @Column(name = "borrow_status", nullable = false)
    private String borrowStatus;

    @Column(name = "fine_fee")
    private Double fineFee;

    @Column(name = "is_fine_paid")
    private Boolean isFinePaid = false;

    @Column(name = "is_lost")
    private Boolean isLost = false;

    @Column(name = "fine_per_day")
    private Double finePerDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBorrowerUserId() {
        return borrowerUserId;
    }

    public void setBorrowerUserId(Long borrowerUserId) {
        this.borrowerUserId = borrowerUserId;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(String borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public Double getFineFee() {
        return fineFee;
    }

    public void setFineFee(Double fineFee) {
        this.fineFee = fineFee;
    }

    public Boolean getIsFinePaid() {
        return isFinePaid;
    }

    public void setIsFinePaid(Boolean isFinePaid) {
        this.isFinePaid = isFinePaid;
    }

    public Boolean getIsLost() {
        return isLost;
    }

    public void setIsLost(Boolean isLost) {
        this.isLost = isLost;
    }

    public Double getFinePerDay() {
        return finePerDay;
    }

    public void setFinePerDay(Double finePerDay) {
        this.finePerDay = finePerDay;
    }
}
