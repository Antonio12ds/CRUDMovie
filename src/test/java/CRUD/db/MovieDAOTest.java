package CRUD.db;

import CRUD.model.Movie;
import CRUD.util.DBConnection;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MovieDAOTest {
    private static MovieDAO movieDAO;

    @BeforeAll
    static void init() {
        movieDAO = new MovieDAO();
    }

    @Test
    void testCreateAndRead() {
        Movie movie = new Movie(0, "Test Movie", "Test Director", 2025);
        movieDAO.create(movie);

        List<Movie> movies = movieDAO.readAll();
        boolean found = movies.stream().anyMatch(m ->
                m.getTitle().equals("Test Movie") &&
                        m.getAuthor().equals("Test Director") &&
                        m.getYear() == 2025
        );

        assertTrue(found, "Movie should be found after insertion");
    }

    @Test
    void testDelete() {
        Movie movie = new Movie(0, "To Delete", "Someone", 2024);
        movieDAO.create(movie);

        List<Movie> moviesBefore = movieDAO.readAll();
        int lastId = moviesBefore.get(moviesBefore.size() - 1).getId();

        movieDAO.delete(lastId);

        List<Movie> moviesAfter = movieDAO.readAll();
        boolean stillExists = moviesAfter.stream().anyMatch(m -> m.getId() == lastId);

        assertFalse(stillExists, "Movie should be deleted");
    }

    @Test
    void testUpdate() {
        Movie movie = new Movie(0, "To Update", "Someone", 2025);
        movieDAO.create(movie);

        List<Movie> moviesBefore = movieDAO.readAll();
        int lastId = moviesBefore.get(moviesBefore.size() - 1).getId();

        movieDAO.update(new Movie(lastId, "Updated", "Someone", 2025));

        List<Movie> moviesAfter = movieDAO.readAll();

        boolean updated = moviesAfter.stream().anyMatch(m ->
                m.getId() == lastId
                        && Objects.equals(m.getTitle(), "Updated")
                        && m.getAuthor().equals("Someone")
                        && m.getYear() == 2025
        );

        assertTrue(updated, "Movie should be updated");
    }

    @Test
    void testReadAllWhenArrayIsEmpty() {
        String sql = "DELETE FROM movies";

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<Movie> moviesBefore = movieDAO.readAll();
        boolean empty = moviesBefore.isEmpty();
        assertTrue(empty, "The list should be empty");
    }

    @Test
    void testAdding3Movie() {
        for (int i = 1; i < 4; i++) {
            Movie movie = new Movie(0, "Test Movie" + i, "Test Director", 2025);
            movieDAO.create(movie);
        }

        ArrayList<Movie> arrayList = movieDAO.readAll();
        boolean allPresent = true;

        for (int j = 1; j < 4; j++) {
            int movieNumber = j;
            allPresent = arrayList.stream().anyMatch(m -> m.getTitle().equals("Test Movie" + movieNumber)
                    && m.getAuthor().equals("Test Director")
                    && m.getYear() == 2025
            );

            if (!allPresent) {
                break;
            }
        }
        assertTrue(allPresent, "You did not add 3 movie");
    }

    @Test
    void testDelete999999(){
        int fakeId = 999999;

        assertDoesNotThrow(() -> movieDAO.delete(fakeId), "Delete should not throw exception for non-existent ID");
    }
}
