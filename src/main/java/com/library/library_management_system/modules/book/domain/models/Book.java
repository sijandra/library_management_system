package com.library.library_management_system.modules.book.domain.models;

public class Book implements IBook {
    private Long id;
    private String title;
    private String description;
    private String synopsis;
    private Integer price;
    private String bookImageUrl;
    private String publishedDate;
    private String publisherId;
    private String language;
    private String[] authorsId;
    private String[] genreIds;
    private Integer pages;
    private String sectionId;
    private String booksId;

    protected Book(Long id, String title, String description, String synopsis, Integer price, String bookImageUrl,
            String publishedDate, String publisherId, String language, String[] authorsId, String[] genreIds,
            Integer pages, String sectionId, String booksId) {
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
            String bookImageUrl, String publishedDate, String publisherId, String language,
            String[] authorsId, String[] genreIds, Integer pages, String sectionId, String booksId) {
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
    public String getPublisherId() {
        return publisherId;
    }

    @Override
    public void setPublisherId(String publisherId) {
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
    public String[] getAuthorsId() {
        return authorsId;
    }

    @Override
    public void setAuthorsId(String[] authorsId) {
        this.authorsId = authorsId;
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
    public String getBooksId() {
        return booksId;
    }

    @Override
    public void setBooksId(String booksId) {
        this.booksId = booksId;
    }

    @Override
    public String[] getGenreIds() {
        return genreIds;
    }

    @Override
    public void setGenreIds(String[] genreIds) {
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
