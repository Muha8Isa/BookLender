package se.lexicon.MuhaIsa.BookLender.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.MuhaIsa.BookLender.Exceptions.DataDuplicateException;
import se.lexicon.MuhaIsa.BookLender.Exceptions.DataNotFoundException;
import se.lexicon.MuhaIsa.BookLender.Repository.BookRepository;
import se.lexicon.MuhaIsa.BookLender.dto.BookDto;
import se.lexicon.MuhaIsa.BookLender.model.Book;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ModelMapper modelMapper; //used to convert to and from DTO. It has dependency inside pom file.



    @Override
    public List<BookDto> findByReserved(boolean reserved) {
        if(!reserved) throw new IllegalArgumentException("book was reserved"); //IllegalArgumentException?
        List<Book> bookList = bookRepository.findByReserved(reserved);
        return modelMapper.map(bookList, new TypeToken<List<BookDto>>(){}.getType());
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {
        if(!available ) throw new IllegalArgumentException("book was reserved"); //IllegalArgumentException?
        List<Book> bookList = bookRepository.findByAvailable(available);
        return modelMapper.map(bookList, new TypeToken<List<BookDto>>(){}.getType());
    }

    @Override
    public List<BookDto> findByTitle(String title) {
        if(title == null) throw new IllegalArgumentException("title was null");
        List<Book> listBook = bookRepository.findByTitleContains(title);
        if(listBook.isEmpty()) throw new DataNotFoundException("book title was not valid!");
        return modelMapper.map(listBook, new TypeToken<List<BookDto>>(){}.getType());
    }

    @Override
    public BookDto findById(String bookId) {
        if(bookId == null) throw new IllegalArgumentException("book id was null");
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(!optionalBook.isPresent()) throw new DataNotFoundException("book id was not valid!");
        Book entity = optionalBook.get();
        return modelMapper.map(entity, BookDto.class); // Converts (from, to)
    }

    @Override
    public List<BookDto> findAll() {
        List<Book> bookList = bookRepository.findAllByOrderByBookIdDesc();
        return modelMapper.map(bookList, new TypeToken<List<BookDto>>(){}.getType());
    }

    @Override
    public BookDto create(BookDto bookDto) {
        if(bookDto == null) throw new IllegalArgumentException("book data was null");
        if(bookDto.getBookId() != null) throw new IllegalArgumentException("book id should be null or zero");
        Book createdEntity = bookRepository.save(modelMapper.map(bookDto, Book.class));
        return modelMapper.map(createdEntity, BookDto.class);
    }

    @Override
    public void update(BookDto bookDto) {
        if(bookDto == null) throw new IllegalArgumentException("book data was null");
        if(bookDto.getBookId() == null) throw new IllegalArgumentException("book id should not be zero");
        if(!bookRepository.findById(bookDto.getBookId()).isPresent()) throw new DataNotFoundException("data not found Error");
        if(bookRepository.findById(bookDto.getTitle()).isPresent()) throw new DataDuplicateException("duplicate Error");
        bookRepository.save(modelMapper.map(bookDto, Book.class));
    }

    @Override
    public void delete(String bookId) {
        BookDto bookDto = findById(bookId);
        if(bookDto == null) throw new DataNotFoundException("data not found");
        bookRepository.deleteById(bookId);
    }
}
