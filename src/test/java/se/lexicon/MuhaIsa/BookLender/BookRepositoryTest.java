package se.lexicon.MuhaIsa.BookLender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.MuhaIsa.BookLender.Repository.BookRepository;
import se.lexicon.MuhaIsa.BookLender.model.Book;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    BookRepository testBook;
    BigDecimal fine = new BigDecimal("0.50"); //Initialize a value of BigDecimal
    Book addedBook1;
    Book addedBook2;

    @BeforeEach
    public void setup() {
        Book book1 = new Book("The Alchemist", 30, fine, "An allegorical novel tells the story of a guy searching for a treasure");
        Book book2 = new Book("Treasure Island", 30, fine, "An allegorical novel tells the story of a group of buccaneers searching for a treasure on an island");
        addedBook1 = testBook.save(book1);
        addedBook2 = testBook.save(book2);
        assertNotNull(addedBook1);
        assertNotNull(addedBook2);
    }

    @Test
    public void findByIdTest() {
        Optional<Book> optionalBook = testBook.findById(addedBook1.getBookId());
        assertTrue(optionalBook.isPresent());
        Book actualData = optionalBook.get();
        Book expectedData = addedBook1;
        assertEquals(actualData, expectedData);
    }

    @Test
    void findAllTest() {
        List<Book> expectedData = Arrays.asList(addedBook1, addedBook2);
        List<Book> actualData = new ArrayList<>();

       /** Iterable<Book> books = testBook.findAll();
        for (Book book : books) {
            actualData.add(book);
        } **/

       // testBook.findAll().forEach(book -> actualData.add(book));

        testBook.findAll().forEach(actualData::add);
        assertEquals(expectedData, actualData);
    }
    
    @Test
    public void findByTitleContains() {
        List<Book> listBook = testBook.findByTitleContains(addedBook1.getTitle());
        assertFalse(listBook.isEmpty());
        Book actualData = listBook.get(0);
        Book expectedData = addedBook1;
        assertEquals(actualData, expectedData);
    }
   /* @Test
    public void findByAvailableTest() {
        Optional<Book> availableBook = testBook.findByAvailable(addedBook1.isAvailable());
        assertTrue(availableBook.isPresent());
    } */

    @Test
    void deleteBookByIdTest() {
        UUID id = addedBook1.getBookId();
        testBook.deleteById(id);
        Optional<Book> optionalBook = testBook.findById(id);
        assertFalse(optionalBook.isPresent());
        // assertTrue(optionalBook.isEmpty());
    }


}

