package com.library.library_management_system.modules.books.persistence.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.modules.books.domain.models.Books;
import com.library.library_management_system.modules.books.persistence.mappers.BooksMapper;
import com.library.library_management_system.modules.books.persistence.mappers.BooksSchema;

@Repository
public class BooksRepository {
    private final IBooksRepository booksRepository;

    @Autowired
    public BooksRepository(IBooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Books getBooksById(Long id) {
        BooksSchema booksSchema = booksRepository.findById(id).orElse(null);
        return BooksMapper.toDomain(booksSchema);
    }

    public List<Books> getAllBooks() {
        List<BooksSchema> booksSchemas = booksRepository.findAll();
        List<Books> booksDomain = new ArrayList<>();

        for (BooksSchema booksSchema : booksSchemas) {
            Books book = BooksMapper.toDomain(booksSchema);
            booksDomain.add(book);
        }

        return booksDomain;
    }

    public List<Books> getBooksByTitleContaining(String title) {
        List<BooksSchema> booksSchemas = booksRepository.getBooksByTitleContaining(title);
        return booksSchemas.stream()
                .map(BooksMapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<Books> getBooksBySectionId(Long sectionId) {
        List<BooksSchema> booksSchemas = booksRepository.getBooksBySectionId(sectionId);
        return booksSchemas.stream()
                .map(BooksMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Books createBooks(Books books) {
        Books createdBooksWithDefaults = Books.createWithDefaults(books.getId(),
                books.getGenreIds(),
                books.getBookIds(),
                books.getSectionId(),
                books.getTitle());

        BooksSchema booksSchema = BooksMapper.toPersistence(createdBooksWithDefaults);
        BooksSchema savedBooksSchema = booksRepository.save(booksSchema);
        return BooksMapper.toDomain(savedBooksSchema);
    }

    public Books updateBooksById(Long id, Books books) {
        books.setId(id);
        BooksSchema booksSchema = BooksMapper.toPersistence(books);
        BooksSchema updatedBooksSchema = booksRepository.save(booksSchema);
        return BooksMapper.toDomain(updatedBooksSchema);
    }

    public Books findBooksByTitle(String title) {
        BooksSchema booksSchema = booksRepository.findBooksByTitle(title);
        return booksSchema != null ? BooksMapper.toDomain(booksSchema) : null;
    }

    public Books addBookIdToBookIds(Long booksId, Long newBookId) {
        BooksSchema booksSchema = booksRepository.findById(booksId).orElse(null);
        if (booksSchema != null) {
            List<Long> updatedBookIds = new ArrayList<>(Arrays.asList(booksSchema.getBookIds()));

            if (!updatedBookIds.contains(newBookId)) {
                updatedBookIds.add(newBookId);
            }

            booksSchema.setBookIds(updatedBookIds.toArray(Long[]::new));

            Books domainBooksObject = BooksMapper.toDomain(booksSchema);
            domainBooksObject.updateBooksCountsAndAvailability();

            BooksSchema updatedBooksSchema = booksRepository.save(BooksMapper.toPersistence(domainBooksObject));

            return BooksMapper.toDomain(updatedBooksSchema);
        }
        return null;
    }
}
