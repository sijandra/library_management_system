package com.library.library_management_system.modules.books.persistence.mappers;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class BooksSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "genre_id", nullable = false)
    private String genreId;

    @ElementCollection
    @Column(name = "book_ids")
    private String[] bookIds = new String[0];

    @Column(name = "book_count")
    private Integer bookCount;

    @Column(name = "number_of_available_books")
    private Integer numberOfAvailableBooks;

    @Column(name = "section_id")
    private String sectionId;

    @ElementCollection
    @Column(name = "borrowed_book_ids")
    private String[] borrowedBookIds = new String[0];

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String[] getBookIds() {
        return bookIds;
    }

    public void setBookIds(String[] bookIds) {
        this.bookIds = bookIds;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public Integer getNumberOfAvailableBooks() {
        return numberOfAvailableBooks;
    }

    public void setNumberOfAvailableBooks(Integer numberOfAvailableBooks) {
        this.numberOfAvailableBooks = numberOfAvailableBooks;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String[] getBorrowedBookIds() {
        return borrowedBookIds;
    }

    public void setBorrowedBookIds(String[] borrowedBookIds) {
        this.borrowedBookIds = borrowedBookIds;
    }
}
