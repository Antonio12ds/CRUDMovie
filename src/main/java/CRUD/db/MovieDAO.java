package CRUD.db;

import CRUD.model.Movie;
import CRUD.util.DBConnection;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.*;
import java.util.ArrayList;

public class MovieDAO {
    public void create(Movie movie) {
        String sql = "INSERT INTO movies (title, author, year, genre_id) VALUES (?, ?, ?, ?)";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getAuthor());
            preparedStatement.setInt(3, movie.getYear());
            preparedStatement.setInt(4, movie.getGenre());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Movie> readAll() {
        ArrayList<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies";
        try (
                Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                movies.add(new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("year"),
                        resultSet.getInt("genre_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    public void update(Movie movie) {
        String sql = "UPDATE movies SET title = ?, author = ?, year = ?, genre_id = ? WHERE id = ?";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getAuthor());
            preparedStatement.setInt(3, movie.getYear());
            preparedStatement.setInt(4, movie.getId());
            preparedStatement.setInt(5, movie.getGenre());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM movies WHERE id = ?";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Movie> search(String title) {
        ArrayList<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies WHERE title LIKE ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, "%" + title + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                movies.add(new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("year"),
                        resultSet.getInt("genre_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    //Different sorting methods
    public ArrayList<Movie> readAllSortedByYearASC() {
        ArrayList<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies ORDER BY year";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);
        ) {
            while (resultSet.next()) {
                movies.add(new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("year"),
                        resultSet.getInt("genre_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    public ArrayList<Movie> readAllSortedByYearDESC() {
        ArrayList<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies ORDER BY year DESC";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);
        ) {
            while (resultSet.next()) {
                movies.add(new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("year"),
                        resultSet.getInt("genre_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    public ArrayList<Movie> readAllSortedByAuthorASC() {
        ArrayList<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies ORDER BY author";
        try (
                Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                movies.add(new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("year"),
                        resultSet.getInt("genre_id")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    public ArrayList<Movie> readAllSortedByAuthorDESC() {
        ArrayList<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies ORDER BY author DESC";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                movies.add(new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("year"),
                        resultSet.getInt("genre_id")
                        ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    public ArrayList<Movie> readAllSortedByGenre(int id){
        ArrayList<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies WHERE genre_id = " + id;
        try (
                Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                ){
            while (resultSet.next()){
                movies.add(new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("year"),
                        resultSet.getInt("genre_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }
}

