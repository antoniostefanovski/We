package mk.ukim.finki.wp.laboratory1.repository;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.laboratory1.model.Movie;
import mk.ukim.finki.wp.laboratory1.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository {

    List<Movie> Movies;

    public MovieRepository() {

        Movies = new ArrayList<>();

        Movies.add(new Movie("Godfather", "Summary for Godfather", 9.8, new Production("Sony Pictures Entertainment", "USA", "Sony Pic Address")));
        Movies.add(new Movie("Scarface", "Summary for Scarface", 9.4, new Production("Sony Pictures Entertainment", "USA", "Sony Pic Address")));
        Movies.add(new Movie("A Bronx Tale", "Summary for A Bronx Tale", 7.6, new Production("Paramount Pictures", "USA", "Paramount Pics Address")));
        Movies.add(new Movie("Goodfellas", "Summary for Goodfellas", 8.3, new Production("Paramount Pictures", "USA", "Paramount Pics Address")));
        Movies.add(new Movie("The Gentleman", "Summary for The Gentleman", 8.7, new Production("20th Century Studios", "USA", "20th Century Address")));
        Movies.add(new Movie("The Irishman", "Summary for The Irishman", 6.1, new Production("20th Century Studios", "USA", "20th Century Address")));
        Movies.add(new Movie("Donnie Brasco", "Summary for Donnie Brasco", 5.9, new Production("Rez Production", "MK", "Rez Address")));
        Movies.add(new Movie("Casino", "Summary for Casino", 4.5, new Production("Rez Production", "MK", "Rez Address")));
        Movies.add(new Movie("Suburra", "Summary for Suburra", 3.0, new Production("Atlantic Pro Media", "MK", "Atlantic Address")));
        Movies.add(new Movie("Pulp Fiction", "Summary for Pulp Fiction", 2.2, new Production("Atlantic Pro Media", "MK", "Atlantic Address")));

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

    public Optional<Movie> save(String movieTitle, String summary, double rating,
                                Production production) {

        if (production == null) {
            throw new IllegalArgumentException();
        }

        Movie movie = new Movie(movieTitle, summary, rating, production);
        Movies.removeIf(m -> m.getTitle().equals(movieTitle));
        Movies.add(movie);
        return Optional.of(movie);
    }

    public Movie findById(long id) {
        return Movies.stream()
                .filter(p -> p.getId() == id).findFirst().get();
    }

    public void deleteById(Long id) {
        Movies.removeIf(m -> m.getId().equals(id));
    }

}
