import CRUD.db.GenreDAO;
import CRUD.model.Genre;
import CRUD.model.Movie;
import CRUD.db.MovieDAO;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MovieDAO movieDAO = new MovieDAO();
        GenreDAO genreDAO = new GenreDAO();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Movie\n2. View All Movies\n3. Update Movie\n4. Delete Movie\n5. Find Movie\n6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Year: ");
                    int year = scanner.nextInt();

                    ArrayList<Genre> genres = genreDAO.readALL();
                    int genreId = 0;
                    while (true){
                        System.out.println("Chose the genre:");
                        for (Genre genre : genres){
                            System.out.println(genre.getId() + ". " + genre.getGenre());
                        }
                        genreId = scanner.nextInt();
                        if (genreId < 20){
                            break;
                        }

                    }
                    movieDAO.create(new Movie(0, title, author, year, genreId));
                    break;
                case 2:
                    ArrayList<Movie> movies = movieDAO.readAll();
                    for (Movie movie : movies) {
                        String genreTitle = genreDAO.search(movie.getGenre());
                        System.out.println(movie.getId() + ". " + movie.getTitle() + " - " + movie.getAuthor() + " (" + movie.getYear() + ") " + genreTitle);
                    }

                    System.out.println("Sort 1(YES) or 0(NO):");
                    int choiceSort = scanner.nextInt();
                    if (choiceSort == 1) {

                        System.out.println("Sort by:\n1. Year (ascending)\n2. Year (descending)\n3. Author (A-Z)\n4. Author (Z-A)\n5. Genre\n6. Unsorted");
                        int typeSorting = scanner.nextInt();
                        scanner.nextLine();
                        switch (typeSorting) {
                            case 1:
                                ArrayList<Movie> moviesByYearASC = movieDAO.readAllSortedByYearASC();
                                for (Movie movie : moviesByYearASC) {
                                    String genreTitle = genreDAO.search(movie.getGenre());
                                    System.out.println(movie.getId() + ". " + movie.getTitle() + " - " + movie.getAuthor() + " (" + movie.getYear() + ") " + genreTitle);
                                }
                                break;
                            case 2:
                                ArrayList<Movie> moviesByYearDESC = movieDAO.readAllSortedByYearDESC();
                                for (Movie movie : moviesByYearDESC) {
                                    String genreTitle = genreDAO.search(movie.getGenre());
                                    System.out.println(movie.getId() + ". " + movie.getTitle() + " - " + movie.getAuthor() + " (" + movie.getYear() + ") " + genreTitle);
                                }
                                break;
                            case 3:
                                ArrayList<Movie> moviesByAuthorASC = movieDAO.readAllSortedByAuthorASC();
                                for (Movie movie : moviesByAuthorASC) {
                                    String genreTitle = genreDAO.search(movie.getGenre());
                                    System.out.println(movie.getId() + ". " + movie.getTitle() + " - " + movie.getAuthor() + " (" + movie.getYear() + ") " + genreTitle);
                                }
                                break;
                            case 4:
                                ArrayList<Movie> moviesByAuthorDESC = movieDAO.readAllSortedByAuthorDESC();
                                for (Movie movie : moviesByAuthorDESC) {
                                    String genreTitle = genreDAO.search(movie.getGenre());
                                    System.out.println(movie.getId() + ". " + movie.getTitle() + " - " + movie.getAuthor() + " (" + movie.getYear() + ") " + genreTitle);
                                }
                                break;
                            case 5:
                                ArrayList<Genre> genresToSort = genreDAO.readALL();
                                System.out.println("Chose the genre:");
                                for (Genre genreToSort : genresToSort){
                                    System.out.println(genreToSort.getId() + ". " + genreToSort.getGenre());
                                }
                                int genreToSort = scanner.nextInt();
                                ArrayList<Movie> moviesByGenre = movieDAO.readAllSortedByGenre(genreToSort);
                                for (Movie movie : moviesByGenre) {
                                    String genreTitle = genreDAO.search(movie.getGenre());
                                    System.out.println(movie.getId() + ". " + movie.getTitle() + " - " + movie.getAuthor() + " (" + movie.getYear() + ") " + genreTitle);
                                }
                            case 6:
                                break;
                        }

                    } else {
                        break;
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Movie ID to update:");
                    int idUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Author: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Year: ");
                    int newYear = scanner.nextInt();
                    System.out.print("Genre: ");
                    ArrayList<Genre> genresUpdate = genreDAO.readALL();
                    System.out.println("Chose the genre:");
                    for (Genre genre1 : genresUpdate){
                        System.out.println(genre1.getId() + ". " + genre1.getGenre());
                    }
                    int newGenre = scanner.nextInt();
                    movieDAO.update(new Movie(idUpdate, newTitle, newAuthor, newYear, newGenre));
                    break;
                case 4:
                    System.out.print("Movie ID to delete:");
                    int idDelete = scanner.nextInt();
                    movieDAO.delete(idDelete);
                    break;
                case 5:
                    System.out.println("Movie title to search:");
                    String titleToSearch = scanner.nextLine();
                    ArrayList<Movie> moviesFound = movieDAO.search(titleToSearch);
                    for (Movie movie : moviesFound) {
                        System.out.println(movie);
                    }
                    break;
                case 6:
                    return;
            }
        }
    }
}
