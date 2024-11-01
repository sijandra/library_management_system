package com.library.library_management_system.modules.books.domain.models;

public interface IBooks {
    Long getId();

    void setId(Long id);

    Long[] getGenreIds();

    void setGenreId(Long[] genreIds);

    Long[] getBookIds();

    void setBookIds(Long[] bookIds);

    Integer getBookCount();

    void setBookCount(Integer bookCount);

    Integer getNumberOfAvailableBooks();

    void setNumberOfAvailableBooks(Integer numberOfAvailableBooks);

    Long getSectionId();

    void setSectionId(Long sectionId);

    Long[] getBorrowedBookIds();

    void setBorrowedBookIds(Long[] borrowedBookIds);

    String getTitle();

    void setTitle(String title);
}
