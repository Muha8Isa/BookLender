package se.lexicon.MuhaIsa.BookLender.Repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.MuhaIsa.BookLender.model.LibraryUser;

public interface UserRepository extends CrudRepository<LibraryUser, Integer> {
}
