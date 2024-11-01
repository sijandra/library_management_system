package com.library.library_management_system.modules.books.domain.models;

public class Books implements IBooks {
    private Long id;
    private Long[] genreIds;
    private Long[] bookIds;
    private Integer bookCount;
    private Integer numberOfAvailableBooks;
    private Long sectionId;
    private Long[] borrowedBookIds;
    private String title;

    protected Books(Long id, Long[] genreIds, Long[] bookIds, Integer bookCount, Integer numberOfAvailableBooks,
            Long sectionId, Long[] borrowedBookIds, String title) {
        this.id = id;
        this.genreIds = genreIds;
        this.bookIds = bookIds;
        this.bookCount = bookCount;
        this.numberOfAvailableBooks = numberOfAvailableBooks;
        this.sectionId = sectionId;
        this.borrowedBookIds = borrowedBookIds;
        this.title = title;
    }

    public static Books create(Long id, Long[] genreIds, Long[] bookIds, Integer bookCount,
            Integer numberOfAvailableBooks, Long sectionId, Long[] borrowedBookIds, String title) {
        return new Books(id, genreIds, bookIds, bookCount, numberOfAvailableBooks, sectionId, borrowedBookIds, title);
    }

    public static Books createWithDefaults(Long id, Long[] genreIds, Long[] bookIds, Long sectionId, String title) {
        return create(id, genreIds, bookIds, 1, 1, sectionId, new Long[0], title);
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
    public Long[] getGenreIds() {
        return genreIds;
    }

    @Override
    public void setGenreId(Long[] genreIds) {
        this.genreIds = genreIds;
    }

    @Override
    public Long[] getBookIds() {
        return bookIds;
    }

    @Override
    public void setBookIds(Long[] bookIds) {
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
    public Long getSectionId() {
        return sectionId;
    }

    @Override
    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public Long[] getBorrowedBookIds() {
        return borrowedBookIds;
    }

    @Override
    public void setBorrowedBookIds(Long[] borrowedBookIds) {
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

    public void updateBooksCountsAndAvailability() {
        this.bookCount = this.bookIds.length;
        this.numberOfAvailableBooks = Math.max(0, this.bookIds.length - this.borrowedBookIds.length);
    }
}
