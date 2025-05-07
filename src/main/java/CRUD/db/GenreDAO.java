package CRUD.db;

import CRUD.model.Genre;
import CRUD.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class GenreDAO {

    public void create(String genre) {
        String sql = "INSERT INTO genre (genre) VALUES ?";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, genre);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Genre> readALL() {
        ArrayList<Genre> genres = new ArrayList<>();
        String sql = "SELECT * FROM genre";
        try (
                Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                genres.add(new Genre(
                        resultSet.getInt("id"),
                        resultSet.getString("genre")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genres;
    }

    public String search(int id){
        String genre = "";
        String sql = "SELECT * FROM genre WHERE id = ?";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                genre = resultSet.getString("genre");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genre;
    }

    public ArrayList<Genre> search(String genre){
        ArrayList<Genre> genres = new ArrayList<>();
        String sql = "SELECT * FROM genre WHERE genre = ?";
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                ) {
            preparedStatement.setString(1, "%" + genre + "%");
            while (resultSet.next()){
                genres.add(new Genre(resultSet.getInt("id"), resultSet.getString("genre")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genres;
    }

    public void delete(int id){
        String sql = "DELETE FROM genre WHERE id = ?";
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
