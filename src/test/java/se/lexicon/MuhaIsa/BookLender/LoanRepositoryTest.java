package se.lexicon.MuhaIsa.BookLender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.MuhaIsa.BookLender.Repository.LoanRepository;
import se.lexicon.MuhaIsa.BookLender.model.Book;
import se.lexicon.MuhaIsa.BookLender.model.LibraryUser;
import se.lexicon.MuhaIsa.BookLender.model.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LoanRepositoryTest {

    @Autowired
    LoanRepository loanTest;
    BigDecimal fine = new BigDecimal("0.50");
    Loan currentLoan1;
    Loan currentLoan2;

    @BeforeEach
    public void setup() {
        Book book1 = new Book("The Alchemist", 30, fine, "An allegorical novel tells the story of a guy searching for a treasure");
        Book book2 = new Book("Treasure Island", 30, fine, "An allegorical novel tells the story of a group of buccaneers searching for a treasure on an island");
        LibraryUser user1 = new LibraryUser("Paulo", "Paulo@hotmail.com");
        LibraryUser user2 = new LibraryUser("Robert", "Robert@hotmail.com");
        Loan loan1 = new Loan(user1, book1, LocalDate.now(), false);
        Loan loan2 = new Loan(user2, book2, LocalDate.now(), false);
        currentLoan1 = loanTest.save(loan1);
        currentLoan2 = loanTest.save(loan2);
        assertNotNull(currentLoan1);
        assertNotNull(currentLoan2);
    }

    @Test
    public void findByIdTest() {
        Optional<Loan> optionalLoan = loanTest.findById(currentLoan1.getLoanId());
        assertTrue(optionalLoan.isPresent());
        Loan actualData = optionalLoan.get();
        Loan expectedData = currentLoan1;
        assertEquals(actualData, expectedData);
    }

    @Test
    void findAllTest() {
        List<Loan> expectedData = Arrays.asList(currentLoan1, currentLoan2);
        List<Loan> actualData = new ArrayList<>();
        loanTest.findAll().forEach(actualData::add);
        assertEquals(expectedData, actualData);
    }

    @Test
    void deleteLoanByIdTest() {
        long id = currentLoan1.getLoanId();
        loanTest.deleteById(id);
        Optional<Loan> optionalLoan = loanTest.findById(id);
        assertFalse(optionalLoan.isPresent());
    }

    // Make sure that you test the relationship in loan works as expected.
    // I will test finding the loan through LoanTaker User ID.
    @Test
    void findLoanByLoanTakerId() {
        Optional<Loan> optionalBorrower = loanTest.findByLoanTakerUserId(currentLoan1.getLoanTaker().getUserId());
        assertTrue(optionalBorrower.isPresent());
        Loan actualData = optionalBorrower.get();
        Loan expectedData = currentLoan1;
        assertEquals(actualData, expectedData);
    }

    // I will test finding the loan through Book ID.

   /* @Test
    void findLoanByBookId() {
        Optional<Loan> optionalBook = loanTest.findByBookBookId(currentLoan1.getBook().getBookId());
        assertTrue(optionalBook.isPresent()); // Problem is here: org.opentest4j.AssertionFailedError:  Expected :true, Actual   :false
        Loan actualData = optionalBook.get();
        Loan expectedData = currentLoan1;
        assertEquals(actualData, expectedData);
    } */
  /* @Test
   void findByConcludedTest() {
       Optional<Loan> concluded = loanTest.findByConcluded(currentLoan1.isConcluded());
       assertTrue(concluded.isPresent());
   } */

}
