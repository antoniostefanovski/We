package mk.ukim.finki.wp.laboratory1.repository;

import mk.ukim.finki.wp.laboratory1.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {

    List<Movie> Movies;

    public MovieRepository() {

        Movies = new ArrayList<>();

        Movies.add(new Movie("Godfather", "Summary for Godfather", 9.8));
        Movies.add(new Movie("Scarface", "Summary for Scarface", 9.4));
        Movies.add(new Movie("A Bronx Tale", "Summary for A Bronx Tale", 7.6));
        Movies.add(new Movie("Goodfellas", "Summary for Goodfellas", 8.3));
        Movies.add(new Movie("The Gentleman", "Summary for The Gentleman", 8.7));
        Movies.add(new Movie("The Irishman", "Summary for The Irishman", 6.1));
        Movies.add(new Movie("Donnie Brasco", "Summary for Donnie Brasco", 5.9));
        Movies.add(new Movie("Casino", "Summary for Casino", 4.5));
        Movies.add(new Movie("Suburra", "Summary for Suburra", 3.0));
        Movies.add(new Movie("Pulp Fiction", "Summary for Pulp Fiction", 2.2));

    }

    public List<Movie> findAll() {
        return Movies;
    }

    public List<Movie> searchMovies(String text) {
        return Movies.stream()
                .filter(m -> m.getTitle().contains(text) || m.getSummary().contains(text))
                .toList();
    }

    public List<Movie> searchMovies(String text, Long rating) {
        return Movies.stream()
                .filter(m -> (m.getTitle().contains(text) || m.getSummary().contains(text)) && m.getRating() >= rating)
                .toList();
    }

}
