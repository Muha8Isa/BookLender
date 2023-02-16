package se.lexicon.MuhaIsa.BookLender.service;

import se.lexicon.MuhaIsa.BookLender.dto.BookDto;
import se.lexicon.MuhaIsa.BookLender.dto.LoanDto;

import java.util.List;

public interface BookService {
    List<BookDto> findByReserved(boolean reserved);
    List<BookDto> findByAvailable(boolean available);
    List<BookDto> findByTitle(String title);
    List<BookDto> findById(String bookId);
    List<BookDto> findAll();
    LoanDto create(BookDto bookDto);

    void update(BookDto bookDto);

    void delete(boolean bookId);
}
