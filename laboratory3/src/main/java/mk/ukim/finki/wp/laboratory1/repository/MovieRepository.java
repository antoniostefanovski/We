package mk.ukim.finki.wp.laboratory1.repository;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.laboratory1.model.Movie;
import mk.ukim.finki.wp.laboratory1.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findById(long id);
    List<Movie> searchMoviesByTitleContainingIgnoreCase(String title);
    List<Movie> findByTitleContainingIgnoreCaseAndRatingGreaterThanEqual(String title, double rating);
    void deleteById(long id);
    Movie findByTitle(String title);

}
