package mk.ukim.finki.wp.laboratory1.service;

import mk.ukim.finki.wp.laboratory1.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> listAll();
    List<Movie> searchMovies(String text);

    List<Movie> searchMovies(String text, Long rating);

}
