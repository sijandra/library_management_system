package com.library.library_management_system.modules.book.domain.models;

public interface IBook {
    Long getId();

    void setId(Long id);

    String getTitle();

    void setTitle(String title);

    String getDescription();

    void setDescription(String description);

    String getSynopsis();

    void setSynopsis(String synopsis);

    Integer getPrice();

    void setPrice(Integer price);

    String getBookImageUrl();

    void setBookImageUrl(String bookImageUrl);

    String getPublishedDate();

    void setPublishedDate(String publishedDate);

    String getPublisherId();

    void setPublisherId(String publisherId);

    String getLanguage();

    void setLanguage(String language);

    String[] getAuthorsId();

    void setAuthorsId(String[] authorsId);

    String getGenreId();

    void setGenreId(String genreId);

    String getPages();

    void setPages(String pages);

    String getSectionId();

    void setSectionId(String sectionId);

    String getBooksId();

    void setBooksId(String booksId);
}
