package se.lexicon.MuhaIsa.BookLender.Repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.MuhaIsa.BookLender.model.Book;

import java.util.UUID;

public interface BookRepository extends CrudRepository<Book, UUID> {
}
