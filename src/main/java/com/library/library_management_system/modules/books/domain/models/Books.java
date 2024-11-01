package com.library.library_management_system.modules.books.domain.models;

public class Books implements IBooks {
    private Long id;
    private String genreId;
    private String[] bookIds;
    private Integer bookCount;
    private Integer numberOfAvailableBooks;
    private String sectionId;
    private String[] borrowedBookIds;
    private String title;

    protected Books(Long id, String genreId, String[] bookIds, Integer bookCount, Integer numberOfAvailableBooks,
            String sectionId, String[] borrowedBookIds, String title) {
        this.id = id;
        this.genreId = genreId;
        this.bookIds = bookIds;
        this.bookCount = bookCount;
        this.numberOfAvailableBooks = numberOfAvailableBooks;
        this.sectionId = sectionId;
        this.borrowedBookIds = borrowedBookIds;
        this.title = title;
    }

    public static Books create(Long id, String genreId, String[] bookIds, Integer bookCount,
            Integer numberOfAvailableBooks, String sectionId, String[] borrowedBookIds, String title) {
        return new Books(id, genreId, bookIds, bookCount, numberOfAvailableBooks, sectionId, borrowedBookIds, title);
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
    public String getGenreId() {
        return genreId;
    }

    @Override
    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    @Override
    public String[] getBookIds() {
        return bookIds;
    }

    @Override
    public void setBookIds(String[] bookIds) {
        this.bookIds = bookIds;
    }

    @Override
    public Integer getBookCount() {
        return bookCount;
    }

    @Override
    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    @Override
    public Integer getNumberOfAvailableBooks() {
        return numberOfAvailableBooks;
    }

    @Override
    public void setNumberOfAvailableBooks(Integer numberOfAvailableBooks) {
        this.numberOfAvailableBooks = numberOfAvailableBooks;
    }

    @Override
    public String getSectionId() {
        return sectionId;
    }

    @Override
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public String[] getBorrowedBookIds() {
        return borrowedBookIds;
    }

    @Override
    public void setBorrowedBookIds(String[] borrowedBookIds) {
        this.borrowedBookIds = borrowedBookIds;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }
}
