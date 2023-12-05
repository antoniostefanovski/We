package mk.ukim.finki.wp.laboratory1.web.controller;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.laboratory1.model.Movie;
import mk.ukim.finki.wp.laboratory1.model.Production;
import mk.ukim.finki.wp.laboratory1.service.MovieService;
import mk.ukim.finki.wp.laboratory1.service.ProductionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final ProductionService productionService;

    @GetMapping
    public String getMoviesPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("movies", movieService.listAll());

        if(error != null && !error.isEmpty()) {
            model.addAttribute("error", error);
        }

        return "listMovies";
    }

    @GetMapping("/add")
    public String addMoviePage(Model model) {
        List<Production> productions = this.productionService.findAll();
        model.addAttribute("productions", productions);
        return "add-movie";
    }

    @GetMapping("/edit-movie/{movieId}")
    public String editMoviePage(@PathVariable Long movieId, Model model) {
        if(this.movieService.findById(movieId) != null) {
            Movie movie = this.movieService.findById(movieId);
            List<Production> productions = this.productionService.findAll();
            model.addAttribute("moviei", movie);
            model.addAttribute("productions", productions);

            return "/add-movie";
        }
        return "redirect:/movies?error=A movie with the given id does not exist!";
    }

    @PostMapping("/add-movie")
    public String saveMovie(@RequestParam String movieTitle,
                            @RequestParam String summary,
                            @RequestParam double rating,
                            @RequestParam long productionId)
    {
        var prod = productionService.findById(productionId);
        this.movieService.save(movieTitle, summary, rating, prod);

        return "redirect:/movies";
    }

    @GetMapping("/delete-movie/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.movieService.deleteById(id);
        return "redirect:/movies";
    }

}
