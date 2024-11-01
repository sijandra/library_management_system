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

    Long getPublisherId();

    void setPublisherId(Long publisherId);

    String getLanguage();

    void setLanguage(String language);

    Long[] getAuthorsId();

    void setAuthorsId(Long[] authorsId);

    Long[] getGenreIds();

    void setGenreIds(Long[] genreIds);

    Integer getPages();

    void setPages(Integer pages);

    Long getSectionId();

    void setSectionId(Long sectionId);

    Long getBooksId();

    void setBooksId(Long booksId);
}
