package se.lexicon.MuhaIsa.BookLender.Repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.MuhaIsa.BookLender.model.LibraryUser;
import se.lexicon.MuhaIsa.BookLender.model.Loan;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    Optional<Loan> findByLoanTakerUserId(Integer userId);
    Optional<Loan> findByBookBookId(UUID bookId);
}
