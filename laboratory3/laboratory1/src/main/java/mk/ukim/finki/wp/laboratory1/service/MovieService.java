package mk.ukim.finki.wp.laboratory1.service;

import mk.ukim.finki.wp.laboratory1.model.Movie;
import mk.ukim.finki.wp.laboratory1.model.Production;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MovieService {

    List<Movie> listAll();
    List<Movie> searchMovies(String text);

    List<Movie> searchMovies(String text, Long rating);

    Optional<Movie> save(String movieTitle, String summary, double rating,
                         Production production);

    Movie findById(long id);

    Movie findByTitle(String title);

    void deleteById(Long id);

}
