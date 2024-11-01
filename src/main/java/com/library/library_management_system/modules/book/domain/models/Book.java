package com.library.library_management_system.modules.book.domain.models;

public class Book implements IBook {
    private Long id;
    private String title;
    private String description;
    private String synopsis;
    private Integer price;
    private String bookImageUrl;
    private String publishedDate;
    private Long publisherId;
    private String language;
    private Long[] authorsId;
    private Long[] genreIds;
    private Integer pages;
    private Long sectionId;
    private Long booksId;

    protected Book(Long id, String title, String description, String synopsis, Integer price, String bookImageUrl,
            String publishedDate, Long publisherId, String language, Long[] authorsId, Long[] genreIds,
            Integer pages, Long sectionId, Long booksId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.synopsis = synopsis;
        this.price = price;
        this.bookImageUrl = bookImageUrl;
        this.publishedDate = publishedDate;
        this.publisherId = publisherId;
        this.language = language;
        this.authorsId = authorsId;
        this.genreIds = genreIds;
        this.pages = pages;
        this.sectionId = sectionId;
        this.booksId = booksId;
    }

    public static Book create(Long id, String title, String description, String synopsis, Integer price,
            String bookImageUrl, String publishedDate, Long publisherId, String language,
            Long[] authorsId, Long[] genreIds, Integer pages, Long sectionId, Long booksId) {
        return new Book(id, title, description, synopsis, price, bookImageUrl, publishedDate, publisherId,
                language, authorsId, genreIds, pages, sectionId, booksId);
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
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getSynopsis() {
        return synopsis;
    }

    @Override
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String getBookImageUrl() {
        return bookImageUrl;
    }

    @Override
    public void setBookImageUrl(String bookImageUrl) {
        this.bookImageUrl = bookImageUrl;
    }

    @Override
    public String getPublishedDate() {
        return publishedDate;
    }

    @Override
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public Long getPublisherId() {
        return publisherId;
    }

    @Override
    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public Long[] getAuthorsId() {
        return authorsId;
    }

    @Override
    public void setAuthorsId(Long[] authorsId) {
        this.authorsId = authorsId;
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
    public Long getBooksId() {
        return booksId;
    }

    @Override
    public void setBooksId(Long booksId) {
        this.booksId = booksId;
    }

    @Override
    public Long[] getGenreIds() {
        return genreIds;
    }

    @Override
    public void setGenreIds(Long[] genreIds) {
        this.genreIds = genreIds;
    }

    @Override
    public Integer getPages() {
        return pages;
    }

    @Override
    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
