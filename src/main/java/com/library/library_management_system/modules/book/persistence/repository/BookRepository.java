package com.library.library_management_system.modules.book.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.modules.book.domain.models.Book;
import com.library.library_management_system.modules.book.persistence.mappers.BookMapper;
import com.library.library_management_system.modules.book.persistence.mappers.BookSchema;

@Repository
public class BookRepository {
    private final IBookRepository bookRepository;

    @Autowired
    public BookRepository(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        List<BookSchema> bookSchemas = bookRepository.findAll();
        List<Book> bookDomain = new ArrayList<>();

        for (BookSchema bookSchema : bookSchemas) {
            Book book = BookMapper.toDomain(bookSchema);
            bookDomain.add(book);
        }

        return bookDomain;
    }

    public Book getBookById(Long id) {
        BookSchema bookSchema = bookRepository.findById(id).orElse(null);
        return BookMapper.toDomain(bookSchema);
    }

    public List<Book> getBookByTitle(String title) {
        List<BookSchema> bookSchemas = bookRepository.findByTitleContaining(title);
        return bookSchemas.stream()
                .map(BookMapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByGenreIds(Long[] genreIds) {
        List<BookSchema> bookSchemas = bookRepository.findByGenreIds(genreIds);
        List<Book> books = new ArrayList<>();

        for (BookSchema bookSchema : bookSchemas) {
            books.add(BookMapper.toDomain(bookSchema));
        }

        return books;
    }

    public List<Book> getBookBySectionId(Long sectionId) {
        List<BookSchema> bookSchemas = bookRepository.findBySectionId(sectionId);
        List<Book> books = new ArrayList<>();

        for (BookSchema bookSchema : bookSchemas) {
            books.add(BookMapper.toDomain(bookSchema));
        }

        return books;
    }

    public Book createBook(Book book) {
        BookSchema bookSchema = BookMapper.toPersistence(book);
        BookSchema savedBookSchema = bookRepository.save(bookSchema);
        return BookMapper.toDomain(savedBookSchema);
    }

    public Book updateBooksId(Long bookId, Long booksId) {
        BookSchema existingBookSchema = bookRepository.findById(bookId).orElse(null);
        
        if (existingBookSchema == null) {
            throw new RuntimeException("Book not found with ID: " + bookId);
        }
        
        existingBookSchema.setBooksId(booksId);
        BookSchema updatedBookSchema = bookRepository.save(existingBookSchema);
        
        return BookMapper.toDomain(updatedBookSchema);
    }
}
