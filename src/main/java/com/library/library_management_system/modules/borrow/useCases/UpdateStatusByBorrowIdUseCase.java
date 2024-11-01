package com.library.library_management_system.modules.borrow.useCases;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.book.domain.models.Book;
import com.library.library_management_system.modules.book.useCases.GetBookByIdUseCase;
import com.library.library_management_system.modules.books.domain.models.Books;
import com.library.library_management_system.modules.books.persistence.mappers.BooksMapper;
import com.library.library_management_system.modules.books.persistence.mappers.BooksSchema;
import com.library.library_management_system.modules.books.persistence.repository.IBooksRepository;
import com.library.library_management_system.modules.books.useCases.GetBooksByIdUseCase;
import com.library.library_management_system.modules.borrow.domain.models.Borrow;
import com.library.library_management_system.modules.borrow.persistence.repository.BorrowRepository;

@Component
public class UpdateStatusByBorrowIdUseCase {
    private final BorrowRepository borrowRepository;
    private final GetBookByIdUseCase getBookByIdUseCase;
    private final GetBooksByIdUseCase getBooksByIdUseCase;
    private final IBooksRepository booksRepository;

    @Autowired
    public UpdateStatusByBorrowIdUseCase(BorrowRepository borrowRepository, GetBookByIdUseCase getBookByIdUseCase,
            GetBooksByIdUseCase getBooksByIdUseCase,
            IBooksRepository booksRepository) {
        this.borrowRepository = borrowRepository;
        this.getBookByIdUseCase = getBookByIdUseCase;
        this.getBooksByIdUseCase = getBooksByIdUseCase;
        this.booksRepository = booksRepository;
    }

    public Borrow execute(Long id, String borrowStatus) {
        Borrow borrow = borrowRepository.getBorrowById(id);

        Book foundBook = getBookByIdUseCase.execute(borrow.getBookId());

        if (foundBook != null) {
            Books foundBooks = getBooksByIdUseCase.execute(foundBook.getId());

            if (foundBooks != null) {
                if (foundBooks.getNumberOfAvailableBooks() == 0) {
                    return null;
                }

                Long[] currentBorrowedBookIds = foundBooks.getBorrowedBookIds();

                Boolean isBookIdToBeReturnedInsideBorrowedBooks = this.isBookBorrowed(borrow.getBookId(),
                        currentBorrowedBookIds);

                if (!Boolean.TRUE.equals(isBookIdToBeReturnedInsideBorrowedBooks)) {
                    return null;
                }

                Long[] updatedBorrowedBookIds = Arrays.stream(currentBorrowedBookIds)
                        .filter(borrowedBookId -> !borrowedBookId.equals(borrow.getBookId()))
                        .toArray(Long[]::new);

                foundBooks.setBorrowedBookIds(updatedBorrowedBookIds);

                foundBooks.updateBooksCountsAndAvailability();

                BooksSchema foundBooksSchema = BooksMapper.toPersistence(foundBooks);

                booksRepository.save(foundBooksSchema);

                return this.borrowRepository.updateStatusByBorrowById(id, borrowStatus);
            }
        }

        return null;
    }

    public boolean isBookBorrowed(Long bookId, Long[] borrowedBookIds) {
        return Arrays.asList(borrowedBookIds).contains(bookId);
    }
}
