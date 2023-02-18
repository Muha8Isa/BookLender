package se.lexicon.MuhaIsa.BookLender.Repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.MuhaIsa.BookLender.model.LibraryUser;
import se.lexicon.MuhaIsa.BookLender.model.Loan;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    Optional<Loan> findByLoanTakerUserId(Integer userId);
    List<Loan> findAllByLoanTakerUserId(Integer userId);
    Optional<Loan> findByBookBookId(String bookId);
    List<Loan> findAllByBookBookId(String bookId);
    List<Loan> findByConcluded(Boolean concluded);


}
