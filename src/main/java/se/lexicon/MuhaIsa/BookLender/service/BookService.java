package se.lexicon.MuhaIsa.BookLender.service;

import se.lexicon.MuhaIsa.BookLender.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> findByReserved(boolean reserved);
    List<BookDto> findByAvailable(boolean available);
    List<BookDto> findByTitle(String title);
    BookDto findById(String bookId);
    List<BookDto> findAll();
    BookDto create(BookDto bookDto);

    void update(BookDto bookDto);

    void delete(String bookId);
}
