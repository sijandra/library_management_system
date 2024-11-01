package com.library.library_management_system.modules.book.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.book.domain.models.Book;
import com.library.library_management_system.modules.book.persistence.repository.BookRepository;

@Component
public class GetBookBySectionIdUseCase {
    private final BookRepository bookRepository;

    @Autowired
    public GetBookBySectionIdUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> execute(Long sectionId) {
        return this.bookRepository.getBookBySectionId(sectionId);
    }
}
