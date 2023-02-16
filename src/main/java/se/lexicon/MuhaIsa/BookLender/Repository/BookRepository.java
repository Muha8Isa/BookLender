package se.lexicon.MuhaIsa.BookLender.Repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.MuhaIsa.BookLender.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, String> {
    List<Book> findByAvailable(boolean availableBook);
    Optional<Book> findByReserved(boolean reservedBook);
    List<Book> findByTitleContains(String title);
}
