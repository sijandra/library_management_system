package com.library.library_management_system.modules.book.persistence.mappers;

import com.library.library_management_system.modules.book.domain.models.Book;

public class BookMapper {
    private BookMapper() {
    }

    public static Book toDomain(BookSchema bookSchema) {
        if (bookSchema == null) {
            return null;
        }

        return Book.create(
                bookSchema.getId(),
                bookSchema.getTitle(),
                bookSchema.getDescription(),
                bookSchema.getSynopsis(),
                bookSchema.getPrice(),
                bookSchema.getBookImageUrl(),
                bookSchema.getPublishedDate(),
                bookSchema.getPublisherId(),
                bookSchema.getLanguage(),
                bookSchema.getAuthorsId(),
                bookSchema.getGenreIds(),
                bookSchema.getPages(),
                bookSchema.getSectionId(),
                bookSchema.getBooksId());
    }

    public static BookSchema toPersistence(Book bookDomain) {
        if (bookDomain == null) {
            return null;
        }

        BookSchema bookSchema = new BookSchema();
        bookSchema.setId(bookDomain.getId());
        bookSchema.setTitle(bookDomain.getTitle());
        bookSchema.setDescription(bookDomain.getDescription());
        bookSchema.setSynopsis(bookDomain.getSynopsis());
        bookSchema.setPrice(bookDomain.getPrice());
        bookSchema.setBookImageUrl(bookDomain.getBookImageUrl());
        bookSchema.setPublishedDate(bookDomain.getPublishedDate());
        bookSchema.setPublisherId(bookDomain.getPublisherId());
        bookSchema.setLanguage(bookDomain.getLanguage());
        bookSchema.setAuthorsId(bookDomain.getAuthorsId());
        bookSchema.setGenreIds(bookDomain.getGenreIds());
        bookSchema.setPages(bookDomain.getPages());
        bookSchema.setSectionId(bookDomain.getSectionId());
        bookSchema.setBooksId(bookDomain.getBooksId());

        return bookSchema;
    }
}
