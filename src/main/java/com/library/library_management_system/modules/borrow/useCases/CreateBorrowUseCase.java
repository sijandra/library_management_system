package com.library.library_management_system.modules.borrow.useCases;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
import com.library.library_management_system.modules.user.domain.models.User;
import com.library.library_management_system.modules.user.persistence.mappers.UserMapper;
import com.library.library_management_system.modules.user.persistence.mappers.UserSchema;
import com.library.library_management_system.modules.user.persistence.repository.IUserRepository;
import com.library.library_management_system.modules.user.useCases.GetByUserIdUseCase;

@Component
public class CreateBorrowUseCase {
    private final BorrowRepository borrowRepository;
    private final GetBookByIdUseCase getBookByIdUseCase;
    private final GetBooksByIdUseCase getBooksByIdUseCase;
    private final GetByUserIdUseCase getByUserIdUseCase;
    private final IBooksRepository booksRepository;
    private final IUserRepository userRepository;

    @Autowired
    public CreateBorrowUseCase(BorrowRepository borrowRepository, GetBookByIdUseCase getBookByIdUseCase,
            GetBooksByIdUseCase getBooksByIdUseCase, GetByUserIdUseCase getByUserIdUseCase,
            IBooksRepository booksRepository, IUserRepository userRepository) {
        this.borrowRepository = borrowRepository;
        this.getBookByIdUseCase = getBookByIdUseCase;
        this.getBooksByIdUseCase = getBooksByIdUseCase;
        this.getByUserIdUseCase = getByUserIdUseCase;
        this.booksRepository = booksRepository;
        this.userRepository = userRepository;
    }

    public Borrow execute(Borrow borrow) {
        Book foundBook = getBookByIdUseCase.execute(borrow.getBookId());

        if (foundBook != null) {
            Books foundBooks = getBooksByIdUseCase.execute(foundBook.getId());

            if (foundBooks != null) {
                if (foundBooks.getNumberOfAvailableBooks() == 0) {
                    return null;
                }

                Long[] currentBorrowedBookIds = foundBooks.getBorrowedBookIds();

                Boolean isBookIdToBeBorrowedInsideBorrowedBooks = this.isBookBorrowed(borrow.getBookId(),
                        currentBorrowedBookIds);

                if (Boolean.TRUE.equals(isBookIdToBeBorrowedInsideBorrowedBooks)) {
                    return null;
                }

                Long[] updatedBorrowedBookIds = Arrays.copyOf(currentBorrowedBookIds,
                        currentBorrowedBookIds.length + 1);

                updatedBorrowedBookIds[currentBorrowedBookIds.length] = borrow.getBookId();

                foundBooks.setBorrowedBookIds(updatedBorrowedBookIds);

                foundBooks.updateBooksCountsAndAvailability();

                BooksSchema foundBooksSchema = BooksMapper.toPersistence(foundBooks);

                booksRepository.save(foundBooksSchema);

                User user = getByUserIdUseCase.execute(borrow.getBorrowerUserId());

                Long[] existingBookIds = user.getBooksBorrowed();

                Set<Long> updatedBookIdsSet = new HashSet<>(Arrays.asList(existingBookIds));

                updatedBookIdsSet.add(borrow.getBookId());

                Long[] updatedBookIds = updatedBookIdsSet.toArray(new Long[0]);

                user.setBooksBorrowed(updatedBookIds);

                UserSchema userSchema = UserMapper.toPersistence(user);

                userRepository.save(userSchema);

                return this.borrowRepository.createBorrow(borrow);
            }
        }

        return null;
    }

    public boolean isBookBorrowed(Long bookId, Long[] borrowedBookIds) {
        return Arrays.asList(borrowedBookIds).contains(bookId);
    }
}
