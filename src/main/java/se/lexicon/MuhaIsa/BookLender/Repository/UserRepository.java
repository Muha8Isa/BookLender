package se.lexicon.MuhaIsa.BookLender.Repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.MuhaIsa.BookLender.model.LibraryUser;

import java.util.Optional;

public interface UserRepository extends CrudRepository<LibraryUser, Integer> {
    Optional<LibraryUser> findByEmail (String email);

}
