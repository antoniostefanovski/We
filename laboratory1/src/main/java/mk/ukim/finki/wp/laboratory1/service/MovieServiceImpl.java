package mk.ukim.finki.wp.laboratory1.service;

import mk.ukim.finki.wp.laboratory1.model.Movie;
import mk.ukim.finki.wp.laboratory1.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return this.movieRepository.searchMovies(text);
    }

    @Override
    public List<Movie> searchMovies(String text, Long rating) {
        return this.movieRepository.searchMovies(text, rating);
    }
}
