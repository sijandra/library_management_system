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
    private Long[] genreIds;

    @ElementCollection
    @Column(name = "book_ids")
    private Long[] bookIds = new Long[0];

    @Column(name = "book_count")
    private Integer bookCount;

    @Column(name = "number_of_available_books")
    private Integer numberOfAvailableBooks;

    @Column(name = "section_id")
    private Long sectionId;

    @ElementCollection
    @Column(name = "borrowed_book_ids")
    private Long[] borrowedBookIds = new Long[0];

    @Column(unique = true)
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(Long[] genreIds) {
        this.genreIds = genreIds;
    }

    public Long[] getBookIds() {
        return bookIds;
    }

    public void setBookIds(Long[] bookIds) {
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

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long[] getBorrowedBookIds() {
        return borrowedBookIds;
    }

    public void setBorrowedBookIds(Long[] borrowedBookIds) {
        this.borrowedBookIds = borrowedBookIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
