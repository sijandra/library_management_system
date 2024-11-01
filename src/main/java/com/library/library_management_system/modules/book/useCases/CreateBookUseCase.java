package com.library.library_management_system.modules.book.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.book.domain.models.Book;
import com.library.library_management_system.modules.book.persistence.repository.BookRepository;
import com.library.library_management_system.modules.books.domain.models.Books;
import com.library.library_management_system.modules.books.useCases.AddBookIdToBookIdsUseCase;
import com.library.library_management_system.modules.books.useCases.CreateBooksUseCase;
import com.library.library_management_system.modules.books.useCases.GetBooksByExactTitleUseCase;

@Component
public class CreateBookUseCase {
    private final BookRepository bookRepository;
    private final CreateBooksUseCase createBooksUseCase;
    private final GetBooksByExactTitleUseCase getBooksByExactTitleUseCase;
    private final AddBookIdToBookIdsUseCase addBookIdToBookIdsUseCase;

    @Autowired
    public CreateBookUseCase(BookRepository bookRepository, CreateBooksUseCase createBooksUseCase,
            GetBooksByExactTitleUseCase getBooksByExactTitleUseCase,
            AddBookIdToBookIdsUseCase addBookIdToBookIdsUseCase) {
        this.bookRepository = bookRepository;
        this.createBooksUseCase = createBooksUseCase;
        this.getBooksByExactTitleUseCase = getBooksByExactTitleUseCase;
        this.addBookIdToBookIdsUseCase = addBookIdToBookIdsUseCase;
    }

    public Book execute(Book book) {
        Books booksFound = this.getBooksByExactTitleUseCase.execute(book.getTitle());

        Book createdBook = this.bookRepository.createBook(book);

        if (createdBook == null) {
            throw new RuntimeException("Failed to create the book.");
        }

        if (booksFound == null) {
            Books createDefaultsForBooks = Books.createWithDefaults(null,
                    book.getGenreIds(),
                    new Long[] { createdBook.getId() },
                    book.getSectionId(),
                    book.getTitle());

            Books createdBooks = this.createBooksUseCase.execute(createDefaultsForBooks);

            createdBook = this.bookRepository.updateBooksId(createdBook.getId(), createdBooks.getId());
        } else {
            this.addBookIdToBookIdsUseCase.execute(booksFound.getId(), createdBook.getId());
            createdBook = this.bookRepository.updateBooksId(createdBook.getId(), booksFound.getId());
        }

        return createdBook;
    }
}
