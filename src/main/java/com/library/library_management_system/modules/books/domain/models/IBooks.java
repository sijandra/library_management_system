package com.library.library_management_system.modules.books.domain.models;

public interface IBooks {
    Long getId();

    void setId(Long id);

    String getGenreId();

    void setGenreId(String genreId);

    String[] getBookIds();

    void setBookIds(String[] bookIds);

    Integer getBookCount();

    void setBookCount(Integer bookCount);

    Integer getNumberOfAvailableBooks();

    void setNumberOfAvailableBooks(Integer numberOfAvailableBooks);

    String getSectionId();

    void setSectionId(String sectionId);

    String[] getBorrowedBookIds();

    void setBorrowedBookIds(String[] borrowedBookIds);

    String getTitle();

    void setTitle(String title);
}
