package mk.ukim.finki.wp.laboratory1.service.impl;

import mk.ukim.finki.wp.laboratory1.model.Movie;
import mk.ukim.finki.wp.laboratory1.model.Production;
import mk.ukim.finki.wp.laboratory1.repository.MovieRepository;
import mk.ukim.finki.wp.laboratory1.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    public List<Movie> listAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return this.movieRepository.searchMoviesByTitleContainingIgnoreCase(text);
    }

    @Override
    public List<Movie> searchMovies(String text, Long rating) {
        return this.movieRepository.findByTitleContainingIgnoreCaseAndRatingGreaterThanEqual(text, rating);
    }

    @Override
    public Optional<Movie> save(String movieTitle, String summary, double rating,
                                Production production) {

        if (production == null) {
            throw new IllegalArgumentException();
        }

        Movie movie = new Movie(movieTitle, summary, rating, production);
        movieRepository.save(movie);
        return Optional.of(movie);
    }

    @Override
    public Movie findById(long id) {
        return this.movieRepository.findById(id);
    }

    @Override
    public Movie findByTitle(String title) {
        return this.movieRepository.findByTitle(title);
    }


    @Override
    public void deleteById(Long id) {
        this.movieRepository.deleteById(id);
    }


}
