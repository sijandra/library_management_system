package com.library.library_management_system.modules.books.persistence.mappers;

import com.library.library_management_system.modules.books.domain.models.Books;

public class BooksMapper {
    private BooksMapper() {
    }

    public static Books toDomain(BooksSchema booksSchema) {
        if (booksSchema == null) {
            return null;
        }
        return Books.create(
                booksSchema.getId(),
                booksSchema.getGenreIds(),
                booksSchema.getBookIds(),
                booksSchema.getBookCount(),
                booksSchema.getNumberOfAvailableBooks(),
                booksSchema.getSectionId(),
                booksSchema.getBorrowedBookIds(),
                booksSchema.getTitle());
    }

    public static BooksSchema toPersistence(Books booksDomain) {
        if (booksDomain == null) {
            return null;
        }

        BooksSchema booksSchema = new BooksSchema();
        booksSchema.setId(booksDomain.getId());
        booksSchema.setGenreIds(booksDomain.getGenreIds());
        booksSchema.setBookIds(booksDomain.getBookIds());
        booksSchema.setBookCount(booksDomain.getBookCount());
        booksSchema.setNumberOfAvailableBooks(booksDomain.getNumberOfAvailableBooks());
        booksSchema.setSectionId(booksDomain.getSectionId());
        booksSchema.setBorrowedBookIds(booksDomain.getBorrowedBookIds());
        booksSchema.setTitle(booksDomain.getTitle());

        return booksSchema;
    }
}
