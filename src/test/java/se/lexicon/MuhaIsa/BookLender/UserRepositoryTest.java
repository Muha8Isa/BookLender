package se.lexicon.MuhaIsa.BookLender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.MuhaIsa.BookLender.Repository.UserRepository;
import se.lexicon.MuhaIsa.BookLender.model.Book;
import se.lexicon.MuhaIsa.BookLender.model.LibraryUser;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository testUser;
    LibraryUser addedUser;

    @BeforeEach
    public void setup() {
        LibraryUser user = new LibraryUser("Test","Test@Test.com");
        addedUser = testUser.save(user);
        assertNotNull(addedUser);
    }
    @Test
    public void findByIdTest() {
        Optional<LibraryUser> optionalUser = testUser.findById(addedUser.getUserId());
        assertTrue(optionalUser.isPresent());
        LibraryUser actualData = optionalUser.get();
        LibraryUser expectedData = addedUser;
        assertEquals(actualData, expectedData);
    }

    @Test
    public void findByIdEmail() {
        Optional<LibraryUser> optionalUser = testUser.findByEmail(addedUser.getEmail());
        assertTrue(optionalUser.isPresent());
        LibraryUser actualData = optionalUser.get();
        LibraryUser expectedData = addedUser;
        assertEquals(actualData, expectedData);
    }
    @Test
    void deleteBookByIdTest() {
        Integer id = addedUser.getUserId();
        testUser.deleteById(id);
        Optional<LibraryUser> optionalUser = testUser.findById(id);
        assertFalse(optionalUser.isPresent());
        // assertTrue(optionalBook.isEmpty());
    }
}
